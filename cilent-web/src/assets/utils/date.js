const date = {
  formatDate (time, format) {
    if (!time) {
      return ''
    }
    format = format || 'yyyy-MM-dd'
    const year = time.getFullYear() // 年份
    const month = time.getMonth() + 1 // 月份
    const day = time.getDate() // 日
    const hours24 = time.getHours() // 小时
    const hours = hours24 % 12 === 0 ? 12 : hours24 % 12
    const minutes = time.getMinutes() // 分
    const seconds = time.getSeconds() // 秒
    const milliseconds = time.getMilliseconds() // 毫秒
    const map = {
      yyyy: year,
      MM: ('0' + month).slice(-2),
      M: month,
      dd: ('0' + day).slice(-2),
      d: day,
      HH: ('0' + hours24).slice(-2),
      H: hours24,
      hh: ('0' + hours).slice(-2),
      h: hours,
      mm: ('0' + minutes).slice(-2),
      m: minutes,
      ss: ('0' + seconds).slice(-2),
      s: seconds,
      S: milliseconds
    }
    return format.replace(/y+|M+|d+|H+|h+|m+|s+|S+/g, function (str) {
      return map[str]
    })
  }
}
// 导出对象，然后在全局注册或在单个vue文件下调用
export default date
