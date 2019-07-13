import { policy } from '@/api/uploadApi'
import { Message } from 'element-ui'
import axios from 'axios'

let nowData = { expire:0 };

export function getPolicyAndUpload(file,path){
  var p = new Promise(function(resolve, reject){

    // 为了减少服务端的压力，设计思路是：初始化上传时，每上传一个文件，获取一次签名。
    // 再上传时，比较当前时间与签名时间，看签名时间是否失效。如果失效，就重新获取一次签名，如果没有失效，就使用之前的签名。
    // 判断expire的值是否超过了当前时间，如果超过了当前时间，就重新获取签名，缓冲时间为10秒。
    let now = Date.parse(new Date());

    if (nowData.expire < now + 10*1000) {
      policy().then(data => {
        nowData = data;
        let formData = genFormData(file,path);
        axios.post(nowData.host, formData)
          .then(response => {
            console.log("response == ", response)
            resolve(nowData.host + '/' + formData.get('key'))
          })
          .catch((err) => {
            console.log("err == ", err)
            reject(err);
          })
      }).catch(err => {
        console.log(err)
        reject('生成签名失败');
      })
    }else {
      let formData = genFormData(file,path);
      axios.post(nowData.host, formData)
        .then(response => {
          console.log("response == ", response)
          resolve(nowData.host + '/' + formData.get('key'))
        })
        .catch((err) => {
          console.log("err == ", err)
          reject(err);
        })
    }
  })

  return p;
}

function genFormData(file,path){
  let formData = new FormData();
  let key = nowData.dir + genKey(file)
  formData.append('key', nowData.dir + genKey(file,path)) //存储在oss的文件路径
  formData.append('policy', nowData.policy)
  formData.append('OSSAccessKeyId', nowData.accessKeyId)
  formData.append('success_action_status', '200') //成功后返回的操作码
  // formData.append('callback', nowData.callback)
  formData.append('signature', nowData.signature) //签名
  //如果是base64文件，那么直接把base64字符串转成blob对象进行上传就可以了
  formData.append('file', file)
  formData.append('expire', nowData.expire)

  return formData;
}


export function checkImage (file) {
  const isJPG = (file.type === 'image/jpeg' || file.type === 'image/png')
  const isLt2M = file.size / 1024 / 1024 < 10

  if (!isJPG) {
    Message({
      message: '上传图片只能是 JPG或PNG 格式!',
      type: 'error'
    })
  }
  if (!isLt2M) {
    Message({
      message: '上传图片大小不能超过 10MB!',
      type: 'error'
    })
  }
  return isJPG && isLt2M
}

/**
 * 创建随机字符串文件名
 * @param num
 * @returns {string}
 */
function genKey(file,path){
  let dateTime = dateFormat(new Date(), 'yyyyMMddhhmmss') // 当前时间
  let randomStr = randomString(4)//  4位随机字符串
  let extensionName = file.name.substr(file.name.indexOf('.')) // 文件扩展名
  let fileName = path + '/' + dateTime + '_' + randomStr + extensionName // 文件名字（相对于根目录的路径 + 文件名）
  return fileName
}

function randomString (num) {
  let chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
  let res = ''
  for (let i = 0; i < num; i++) {
    var id = Math.ceil(Math.random() * 35)
    res += chars[id]
  }
  return res
}

function dateFormat (dateObj, format) {
  let date = {
    'M+': dateObj.getMonth() + 1,
    'd+': dateObj.getDate(),
    'h+': dateObj.getHours(),
    'm+': dateObj.getMinutes(),
    's+': dateObj.getSeconds(),
    'q+': Math.floor((dateObj.getMonth() + 3) / 3),
    'S+': dateObj.getMilliseconds()
  }
  if (/(y+)/i.test(format)) {
    format = format.replace(RegExp.$1, (dateObj.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  for (let k in date) {
    if (new RegExp('(' + k + ')').test(format)) {
      format = format.replace(RegExp.$1, RegExp.$1.length === 1
        ? date[k] : ('00' + date[k]).substr(('' + date[k]).length))
    }
  }
  return format
}


