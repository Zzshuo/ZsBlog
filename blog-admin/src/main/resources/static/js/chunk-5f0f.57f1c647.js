(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-5f0f"],{"5z5A":function(e,t,a){"use strict";var i=a("Pp9f");a.n(i).a},COtM:function(e,t,a){},GV2f:function(e,t,a){"use strict";var i=a("tU/I");a.n(i).a},Pp9f:function(e,t,a){},corT:function(e,t,a){"use strict";var i=a("jCqE");a.n(i).a},eOU5:function(e,t,a){"use strict";var i=a("COtM");a.n(i).a},jCqE:function(e,t,a){},lLti:function(e,t,a){"use strict";var i=a("QbLZ"),l=a.n(i),n=a("P2sY"),r=a.n(n),o={name:"MdInput",props:{icon:String,name:String,type:{type:String,default:"text"},value:[String,Number],placeholder:String,readonly:Boolean,disabled:Boolean,min:String,max:String,step:String,minlength:Number,maxlength:Number,required:{type:Boolean,default:!0},autoComplete:{type:String,default:"off"},validateEvent:{type:Boolean,default:!0}},data:function(){return{currentValue:this.value,focus:!1,fillPlaceHolder:null}},computed:{computedClasses:function(){return{"material--active":this.focus,"material--disabled":this.disabled,"material--raised":Boolean(this.focus||this.currentValue)}}},watch:{value:function(e){this.currentValue=e}},methods:{handleModelInput:function(e){var t=e.target.value;this.$emit("input",t),"ElFormItem"===this.$parent.$options.componentName&&this.validateEvent&&this.$parent.$emit("el.form.change",[t]),this.$emit("change",t)},handleMdFocus:function(e){this.focus=!0,this.$emit("focus",e),this.placeholder&&""!==this.placeholder&&(this.fillPlaceHolder=this.placeholder)},handleMdBlur:function(e){this.focus=!1,this.$emit("blur",e),this.fillPlaceHolder=null,"ElFormItem"===this.$parent.$options.componentName&&this.validateEvent&&this.$parent.$emit("el.form.blur",[this.currentValue])}}},s=(a("5z5A"),a("KHd+")),u=Object(s.a)(o,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"material-input__component",class:e.computedClasses},[a("div",{class:{iconClass:e.icon}},[e.icon?a("i",{staticClass:"el-input__icon material-input__icon",class:["el-icon-"+e.icon]}):e._e(),e._v(" "),"email"===e.type?a("input",{directives:[{name:"model",rawName:"v-model",value:e.currentValue,expression:"currentValue"}],staticClass:"material-input",attrs:{name:e.name,placeholder:e.fillPlaceHolder,readonly:e.readonly,disabled:e.disabled,autoComplete:e.autoComplete,required:e.required,type:"email"},domProps:{value:e.currentValue},on:{focus:e.handleMdFocus,blur:e.handleMdBlur,input:[function(t){t.target.composing||(e.currentValue=t.target.value)},e.handleModelInput]}}):e._e(),e._v(" "),"url"===e.type?a("input",{directives:[{name:"model",rawName:"v-model",value:e.currentValue,expression:"currentValue"}],staticClass:"material-input",attrs:{name:e.name,placeholder:e.fillPlaceHolder,readonly:e.readonly,disabled:e.disabled,autoComplete:e.autoComplete,required:e.required,type:"url"},domProps:{value:e.currentValue},on:{focus:e.handleMdFocus,blur:e.handleMdBlur,input:[function(t){t.target.composing||(e.currentValue=t.target.value)},e.handleModelInput]}}):e._e(),e._v(" "),"number"===e.type?a("input",{directives:[{name:"model",rawName:"v-model",value:e.currentValue,expression:"currentValue"}],staticClass:"material-input",attrs:{name:e.name,placeholder:e.fillPlaceHolder,step:e.step,readonly:e.readonly,disabled:e.disabled,autoComplete:e.autoComplete,max:e.max,min:e.min,minlength:e.minlength,maxlength:e.maxlength,required:e.required,type:"number"},domProps:{value:e.currentValue},on:{focus:e.handleMdFocus,blur:e.handleMdBlur,input:[function(t){t.target.composing||(e.currentValue=t.target.value)},e.handleModelInput]}}):e._e(),e._v(" "),"password"===e.type?a("input",{directives:[{name:"model",rawName:"v-model",value:e.currentValue,expression:"currentValue"}],staticClass:"material-input",attrs:{name:e.name,placeholder:e.fillPlaceHolder,readonly:e.readonly,disabled:e.disabled,autoComplete:e.autoComplete,max:e.max,min:e.min,required:e.required,type:"password"},domProps:{value:e.currentValue},on:{focus:e.handleMdFocus,blur:e.handleMdBlur,input:[function(t){t.target.composing||(e.currentValue=t.target.value)},e.handleModelInput]}}):e._e(),e._v(" "),"tel"===e.type?a("input",{directives:[{name:"model",rawName:"v-model",value:e.currentValue,expression:"currentValue"}],staticClass:"material-input",attrs:{name:e.name,placeholder:e.fillPlaceHolder,readonly:e.readonly,disabled:e.disabled,autoComplete:e.autoComplete,required:e.required,type:"tel"},domProps:{value:e.currentValue},on:{focus:e.handleMdFocus,blur:e.handleMdBlur,input:[function(t){t.target.composing||(e.currentValue=t.target.value)},e.handleModelInput]}}):e._e(),e._v(" "),"text"===e.type?a("input",{directives:[{name:"model",rawName:"v-model",value:e.currentValue,expression:"currentValue"}],staticClass:"material-input",attrs:{name:e.name,placeholder:e.fillPlaceHolder,readonly:e.readonly,disabled:e.disabled,autoComplete:e.autoComplete,minlength:e.minlength,maxlength:e.maxlength,required:e.required,type:"text"},domProps:{value:e.currentValue},on:{focus:e.handleMdFocus,blur:e.handleMdBlur,input:[function(t){t.target.composing||(e.currentValue=t.target.value)},e.handleModelInput]}}):e._e(),e._v(" "),a("span",{staticClass:"material-input-bar"}),e._v(" "),a("label",{staticClass:"material-label"},[e._t("default")],2)])])},[],!1,null,"f03f6b9c",null);u.options.__file="index.vue";var d=u.exports,c={name:"Sticky",props:{stickyTop:{type:Number,default:0},zIndex:{type:Number,default:1},className:{type:String,default:""}},data:function(){return{active:!1,position:"",width:void 0,height:void 0,isSticky:!1}},mounted:function(){this.height=this.$el.getBoundingClientRect().height,window.addEventListener("scroll",this.handleScroll),window.addEventListener("resize",this.handleReize)},activated:function(){this.handleScroll()},destroyed:function(){window.removeEventListener("scroll",this.handleScroll),window.removeEventListener("resize",this.handleReize)},methods:{sticky:function(){this.active||(this.position="fixed",this.active=!0,this.width=this.width+"px",this.isSticky=!0)},handleReset:function(){this.active&&this.reset()},reset:function(){this.position="",this.width="auto",this.active=!1,this.isSticky=!1},handleScroll:function(){var e=this.$el.getBoundingClientRect().width;this.width=e||"auto",this.$el.getBoundingClientRect().top<this.stickyTop?this.sticky():this.handleReset()},handleReize:function(){this.isSticky&&(this.width=this.$el.getBoundingClientRect().width+"px")}}},m=Object(s.a)(c,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{style:{height:e.height+"px",zIndex:e.zIndex}},[a("div",{class:e.className,style:{top:e.stickyTop+"px",zIndex:e.zIndex,position:e.position,width:e.width,height:e.height+"px"}},[e._t("default",[a("div",[e._v("sticky")])])],2)])},[],!1,null,null,null);m.options.__file="index.vue";var p=m.exports,h={name:"ImageItem",props:{value:{type:String,default:""}},computed:{imageUrl:{get:function(){return this.value},set:function(e){this.$emit("input",e)}}},methods:{handleAvatarSuccess:function(e,t){this.imageUrl=URL.createObjectURL(t.raw)},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type,a=e.size/1024/1024<2;return t||this.$message.error("上传头像图片只能是 JPG 格式!"),a||this.$message.error("上传头像图片大小不能超过 2MB!"),t&&a},addCoverImage:function(e){var t=this,a=new FormData;a.append("file",e.file),this.api.addCoverImage(a).then(function(e){t.imageUrl=e})}}},f=(a("eOU5"),Object(s.a)(h,function(){var e=this.$createElement,t=this._self._c||e;return t("div",[t("el-upload",{staticClass:"avatar-uploader",attrs:{"show-file-list":!1,"before-upload":this.beforeAvatarUpload,"http-request":this.addCoverImage,action:"none"}},[this.imageUrl?t("img",{staticClass:"avatar",attrs:{src:this.imageUrl}}):t("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1)},[],!1,null,"8f931d9e",null));f.options.__file="Image.vue";var v=f.exports,g=a("L2JU"),y={name:"Type",props:{value:{default:null,type:Number}},data:function(){return{options:[{value:1,label:"前端"},{value:2,label:"后端"},{value:3,label:"其他"}]}},computed:l()({},Object(g.b)(["allType"]),{typeId:{get:function(){return this.value},set:function(e){this.$emit("input",e)}}})},b=(a("GV2f"),Object(s.a)(y,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.typeId,callback:function(t){e.typeId=t},expression:"typeId"}},e._l(this.$store.getters.allType,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))},[],!1,null,"0a208b49",null));b.options.__file="Type.vue";var _=b.exports,x=a("stgD"),F=(a("ZOF2"),{state:0,tagIdList:[]}),C={name:"ArticleDetail",components:{MDinput:d,Sticky:p,mavonEditor:x.mavonEditor,FormItemImage:v,FormItemType:_},props:{isEdit:{type:Boolean,default:!1}},data:function(){return{postForm:r()({},F),loading:!1,rules:{title:[{required:!0,message:"请输入标题",trigger:"change"}],content:[{required:!0,message:"请输入内容",trigger:"change"}],original:[{type:"boolean",required:!0,message:"请选择类型",trigger:"change"}],typeId:[{required:!0,message:"请选择分类",trigger:"change"}],tagIdList:[{type:"array",required:!0,message:"请选择标签",trigger:"change"}]},reqVo:{}}},computed:l()({},Object(g.b)(["allTag"])),created:function(){if(this.isEdit){var e=this.$route.params&&this.$route.params.id;this.fetchData(e)}else this.postForm=r()({},F)},methods:{fetchData:function(e){var t=this;this.reqVo.id=e,this.api.getArticleById(this.reqVo).then(function(e){t.postForm=e})},submitForm:function(){var e=this;this.$refs.postForm.validate(function(t){t&&(e.loading=!0,e.postForm.state=1,e.api.saveArticle(e.postForm).then(function(t){e.$message({message:"发布文章成功",type:"success",duration:1e3}),e.loading=!1,e.$router.push("/article/list")},function(){e.loading=!1}))})},draftForm:function(){var e=this;this.postForm.state=2,this.api.saveArticle(this.postForm).then(function(t){e.$message({message:"保存草稿成功",type:"success",duration:1e3}),e.$router.push("/article/list")})},addArticleImage:function(e,t){var a=this,i=new FormData;i.append("file",t),this.api.addArticleImage(i).then(function(t){a.$refs.md.$img2Url(e,t)})}}},I=(a("corT"),Object(s.a)(C,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"createPost-container"},[a("el-form",{ref:"postForm",staticClass:"form-container",attrs:{model:e.postForm,rules:e.rules,"hide-required-asterisk":""}},[a("sticky",{attrs:{"class-name":"sub-navbar state"+e.postForm.state}},[a("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{"margin-left":"10px"},attrs:{type:"success"},on:{click:function(t){e.submitForm("postForm")}}},[e._v("发布")]),e._v(" "),a("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{type:"warning"},on:{click:e.draftForm}},[e._v("草稿")])],1),e._v(" "),a("div",{staticClass:"createPost-main-container"},[a("el-form-item",{attrs:{prop:"title"}},[a("MDinput",{attrs:{maxlength:100,name:"name",required:""},model:{value:e.postForm.title,callback:function(t){e.$set(e.postForm,"title",t)},expression:"postForm.title"}},[e._v("\n          标题\n        ")])],1),e._v(" "),a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{xs:24,sm:12,md:8,lg:8}},[a("el-form-item",{attrs:{label:"分类:",prop:"typeId"}},[a("form-item-type",{model:{value:e.postForm.typeId,callback:function(t){e.$set(e.postForm,"typeId",t)},expression:"postForm.typeId"}})],1)],1),e._v(" "),a("el-col",{attrs:{xs:24,sm:12,md:8,lg:8}},[a("el-form-item",{attrs:{label:"类型:",prop:"original"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.postForm.original,callback:function(t){e.$set(e.postForm,"original",t)},expression:"postForm.original"}},e._l(this.$store.getters.original,function(e){return a("el-option",{key:e.value,attrs:{label:e.name,value:e.value}})}))],1)],1),e._v(" "),a("el-col",{attrs:{xs:24,sm:12,md:8,lg:8}},[a("el-form-item",{attrs:{label:"标签:",prop:"tagList"}},[a("el-select",{attrs:{multiple:"",filterable:"","allow-create":"","default-first-option":"",placeholder:"请选择文章标签"},model:{value:e.postForm.tagIdList,callback:function(t){e.$set(e.postForm,"tagIdList",t)},expression:"postForm.tagIdList"}},e._l(this.$store.getters.allTag,function(e){return a("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1)],1),e._v(" "),a("el-form-item",{staticStyle:{"margin-bottom":"30px"},attrs:{prop:"content"}},[a("mavon-editor",{ref:"md",staticClass:"mavonEditor",attrs:{"box-shadow":!1},on:{imgAdd:e.addArticleImage},model:{value:e.postForm.content,callback:function(t){e.$set(e.postForm,"content",t)},expression:"postForm.content"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"封面图:"}},[a("form-item-image",{model:{value:e.postForm.image,callback:function(t){e.$set(e.postForm,"image",t)},expression:"postForm.image"}})],1)],1)],1)],1)},[],!1,null,"3c2e017f",null));I.options.__file="ArticleDetail.vue";t.a=I.exports},"tU/I":function(e,t,a){}}]);