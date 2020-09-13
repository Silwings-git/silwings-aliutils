

# 																		silwings-aliutils组件使用文档

### 说明:

1. 该组件对 【使用Java Api】 从服务器上传文件到阿里OSS.VOD服务器进行了封装,简化操作.

2. 功能概述:

   1. OSS:
      1. 引入依赖后通过简单的配置即可通过组件的web接口向阿里OSS服务器上传图片文件.无需手写接口
      2. 提供Service服务对象方便对图片进行操作
      3. 通过配置可进行自动图片放大/压缩.支持多压缩比例配置
   2. VOD:
      1. 通过简单配置即可使用默认web接口上传视频,无需手写接口
      2. 可设置统一视频格式,自动进行格式转换(默认统一为.mp4格式)
      3. 可配置使用同步/异步上传方式(目前只提供二选一)
      4. 提供Service服务对象方便对视频进行操作

3. 注意事项:

   1. 因为阿里VOD的upload包尚未开源,且我也未将该组件上传到中央仓库,故下载源码后需要将`aliyun-java-vod-upload-1.4.12.jar`包自行打包到本地仓库.打包后修改pom文件中的aliutils-vod-upload依赖为你自定义的依赖.

   2. 打包命令:

      ```
      mvn install:install-file -Dfile=E:\Idea-workspace\silwings\aliutils-spring-boot-starter\aliyun-java-vod-upload-1.4.12.jar  -DgroupId=com.silwings -DartifactId=silwings-aliyun-upload -Dversion=1.0 -Dpackaging=jar
      ```

### OSS组件的使用:

1. 导入OSS依赖

2. 添加配置文件

   ```
   必要的配置
   alicustom.openimg=true  开启图片上传服务
   alicustom.imgurl=/路径:自定义上级路径
   
   alicustom.aliimg.endpoint   阿里对象存储endpoint
   alicustom.aliimg.accessKeyId   阿里秘钥
   alicustom.aliimg.accessKeySecret   阿里秘钥
   alicustom.aliimg.bucketName   阿里存储空间
   alicustom.aliimg.compress   是否压缩,默认不压缩
   
   alicustom.compresslevel.scale   缩放比例,集合。用来在前端请求时自动确定压缩等级。
   alicustom.compresslevel.defaultCompressValue   默认缩放比例。缩放比例,值越大压缩后的图片越，小于1压缩，大于1放大。float类型
   ```

   ```
   关于路径的说明:
   1.不声明会启动失败,如果定义为空会直接使用方法路径 /uploadImg 作为图片上传路径.该路径为方法级路径.(定义为空:只声明不写值)
   **注意事项:请不要在自定义注解后添加斜杠"/",否则路径映射可能出错
   2.配置的自定义上传路径并非替换默认路径,而是在其之前添加上方法级路径.例:自定义路径 /diy/path .则合法访问地址为address:prot/diy/path/uploadImg.
   3.上传接口默认的参数有两个.upFile和compressLevel.其中upFile为MultipartFile类型的必传参数.compressLevel为Integer类型的非必传参数,只有开启了图片压缩功能并且传递了合法的参数才会生效.该接口参数使用表单提交.
   ```

   ```
   关于图片压缩的说明:
   1.使用图片压缩需要开启图片压缩功能,需要配置compress为true
   2.通过scale来配置压缩程度.该配置接收一个字符串数组,配置的数组与接口参数compressLevel存在相互作用.如： [0.1,0.2,0.3],那么前端向uploadImg接口请求时只需要携带参数compressLevel=0，即可使用0.1作为压缩等级。compressLevel=2使用0.3作为压缩等级(yml中配置时不需要写中括号,直接逗号分割即可).
   **注意事项:虽然该配置接收字符串数组,但必须配置数值类型.否则会在运行时抛出异常!!!
   3.如果未将compress设置为true配置了scale,将不会生效.
   4.如果配置了compress为true未配置scale会使用默认值进行图片压缩.默认值为0.6
   5.可以通过defaultCompressValue配置默认压缩比例
   6.缩放比例: 值越大压缩后的图片越大，小于1压缩，大于1放大
   7.如果设置了多个scale但请求时未指定compressLevel,默认使用数组0角标的值
   ```

   ```text
   关于接口返回值的说明:
   1./uploadImg接口会返回R<String>结果.如果成功,将在R对象的Data中封装图片的路径.否则在Msg中返回错误信息
   ```

   ```
   关于日志:
   1.项目是否成功适用OSS组件,可以通过启动日志查看.如果成功适用会打印多个 *****初始化完成 的info提示
   2.在图片上传,图片压缩时均会打印相关日志.
   ```

   ```
   其他说明:
   1.OSS服务并非只能存储图片.但因该组件只针对图片进行了封装,所以当开启图片压缩后,web接口将不能接收图片外的其他文件,否则会抛出异常.关闭图片压缩时使用图片上传接口可兼容其他类型文件.
   ```

3. 操作图片

   ```
   1.说明:
   除了图片的上传,可能还需要对图片进行其他操作.目前组件提供了对图片的删除操作.但删除操作属于敏感操作,故并未提供删除用的web接口.如果需要实现图片删除,请自行构建Controller.
   2.如何删除视频:
   组件提供了一个ImgInfoService类,其提供了图片操作的方法.目前版本仅提供了删除功能.(接收一个String集合,元素为包含文件后缀在内的完整路径，例如abc/efg/123.jpg.一次支持删除最多1000个文件.详见阿里云官网)
   3.如何获取ImgInfoService对象:
   使用@Autowired注入即可.
   ```

   

### VOD组件的使用:

1. 导入依赖

2. 添加配置

   ```
   必须的配置:
   alicustom.openvod=true:开启阿里视频点播服务
   alicustom.vodurl=/路径:自定义上级路径(不定义启动失败,如果定义为空会直接使用方法路径)
   
   视频配置:
   alicustom.alivideo.accessKeyId 阿里秘钥(必须要手动配置)
   alicustom.alivideo.accessKeySecret 阿里秘钥(必须要手动配置)
   alicustom.alivideo.maxLength 最大视频长度(默认不限制)(暂时未完成的功能)
   alicustom.alivideo.syncUpload 同步上传(默认使用异步上传,异步上传必须使用redis)
   alicustom.alivideo.printProgress 上传进度打印(默认关闭)
   alicustom.alivideo.videoSuffix 统一视频格式(默认mp4,配置时需要包含".")
   
   缓存配置:
   alicustom.redisprefix.videoSoleId 视频上传状态缓存key前缀(建议配置模块名与使用途径,使用冒号分割,有默认值)
   alicustom.redisprefix.videoDetailSaveId 视频详情缓存key前缀(建议配置模块名与使用途径,使用冒号分割,有默认值)
   
   线程池配置:
   alicustom.threadpool.corePoolSize 核心线程数(默认5)
   alicustom.threadpool.maxPoolSize 最大线程数(默认10)
   alicustom.threadpool.queueCapacity 队列容量(默认20)
   alicustom.threadpool.keepAliveSeconds 线程活跃时间,单位秒(默认60)
   alicustom.threadpool.threadNamePrefix 线程名称前缀(默认"Ali-Video-Thread-")
   ```

   ```
   关于路径的说明:
   1.不声明会启动失败,如果定义为空会直接使用方法路径 /upload 作为图片上传路径.该路径为方法级路径.(定义为空:只声明不写值)
   **注意事项:请不要在自定义注解后添加斜杠"/",否则路径映射可能出错
   2.配置的自定义上传路径并非替换默认路径,而是在其之前添加上方法级路径.例:自定义路径 /diy/path .则合法访问地址为address:prot/diy/path/upload.
   ```

   ```
   关于接口的说明:
   1.上传文件接口: /upload
   	该接口用于视频文件上传.上传接口的参数有两个.hashKey和file.hashKey为String类型的非必传参数(异步必传,同步非必传).file为MultipartFile类型的必传参数.hashKey的作用在于异步上传,之后在异步流程中解释.参数提交方式为表单
   2.获取hashKey接口: /getUpLoadHashKey
   	该接口用于获取/upload需要的hashKey.接收一个String类型表单参数size.必传.启用同步上传时该接口返回错误信息.
   3.获取文件上传状态: /getUpStatus
   	该接口用于在异步上传时获取视频上传状态.接收一个String类型的表单参数hashKey.必传.启用同步上传时该接口返回错误信息.
   ```

   ```
   统一视频格式说明:
   1.默认视频格式
   	组件中强制限制所有视频格式统一.默认将视频转换为.mp4格式的视频.
   2.可以通过videoSuffix修改默认视频格式.
   **注意事项:配置默认视频格式时必须包含".",例:设置视频格式为mp4格式,应该配置: .mp4
   ```

   ```
   缓存配置说明:
   1.异步上传视频时需要在Redis缓存中存储上传状态和文件信息.为了方便维护,建议自定义前缀.
   2.两个字段前缀相同不会有影响但建议不同
   3.自定义的前缀并非直接作为key存入Redis缓存.而是在前拼接固定常量,在后添加hashKey后,作为Redis缓存的key存入缓存库.
   4.固定常量:
   	文件上传状态: ali:utils:videoSoleId:
   	上传文件信息: ali:utils:videoDetailSaveId:
   ```

   ```
   线程池配置:
   1.开启异步上传后会自动使用默认参数加载线程池.
   2.可使用上述配置修改默认线程池配置
   3.线程池名称为"vodTaskExecutor",项目中自定义线程池时注意不要与之重名!!!
   4.该线程池可用于该组件之外.即在其他方法上添加@Async("vodTaskExecutor")即可使用该线程池中的线程
   ```

3. 操作视频资源:

   ```
   1.说明:
   除了视频的上传,可能还需要对视频进行其他操作.目前组件提供了对视频的其他操作.但这些操作属于敏感操作,故并未提供现成的web接口.如果需要实现图片删除,请自行构建Controller.
   2.如何操作视频:
   和OSS一样,VOD组件同样提供了一个VideoInfoService类.其中提供了获取播放地址,获取视频封面,获取播放凭证,删除视频等接口供用户调用.
   3.如何获取操作VideoInfoService类.
   使用@Autowired注入即可.
   ```

4. 同步视频上传:

   1. 接口返回说明:  /upload
      成功示例:

      ```json
      {
          "code": 100200,
          "msg": "SUCCESS",
          "data": {
              "videoId": "4d1ceb84cb2e4b6fa3444ed338f12e83",  	//视频id
              "fileNameId": "501731540271763456.mp4",				//系统生成文件名称
              "title": "501731540271763456.mp4"					//视频标题
          }
      }
      ```

      

   2. 其他说明:

      1. 视频刚上传成功无法获取立即到封面图片(除非自己截取封面图片后设置到视频上传信息中,目前不支持)

5. 异步视频上传:

   1. 接口说明:  /getUpLoadHashKey

      ```
      1.在上传图片之前应调用该接口获取hashKey.hashKey为视频资源定位符.后续的上传,状态获取都需要使用.
      2.hashKey生成规则: 视频大小(size)+系统当前时间毫秒值+uuid.
      例: 20159978962815273b5f947-460f-4bf9-8328-acb1df4ced39
      ```

   2. 接口说明:  /upload

      ```
      1.获取hashKey后,将其与视频文件一同作为参数请求该接口.如果未携带hashKey或hashKey为空字符会提示"上传失败"
      2.hashKey并不一定需要通过"/getUpLoadHashKey"接口获取,但为了保证该资源定位符的唯一性,请在实践中务必通过"/getUpLoadHashKey"接口获取
      3.调起异步线程后,该接口会立即返回,并返回hashkey.
      ```

      成功响应示例:

      ```json
      {
          "code": 100200,
          "msg": "SUCCESS",
          "data": "20159978962815273b5f947-460f-4bf9-8328-acb1df4ced39"
      }
      ```

   3. 接口说明:  /getUpStatus

      ```
      1.应该在/upload接口成功响应之后才调用该接口获取视频上传状态
      2.该接口会返回一个上传状态对象.其中包含一个状态码"upStatus".该状态码为String类型,共三个状态:
      	"0" : 上传中
      	"1" : 上传成功
      	"2" : 上传失败
      3.如果拿到的结果是上传中,应该继续轮询该接口,直到拿到成功或失败.
      ```

      成功示例:

      ```json
      {
          "code": 100200,
          "msg": "SUCCESS",
          "data": {
              "videoId": "79bd7537ff96438c8bebb95db2e48b9d",
              "url": "http://silwings.demo.com/sv/5d0bda8e-1747aejin64/5d0ry583-175h8e9f764.mp4",
              "fileNameId": "501737413788657072.mp4",
              "title": "501737413788657072.mp4",
              "upStatus": "1"
          }
      }
      ```

6. 缓存说明:

   ```
   1.问题:
   	异步上传时,上传状态和视频信息分别存储在Redis缓存中.在用户获取到成功或失败的结果后缓存会被删除.但前端轮询器存在一个问题.当clearInterval轮询器后轮询器可能还会执行一次.请求过来后发现缓存不存在数据,就会响应失败,导致前端数据错误.
   2.解决:
   	为了解决这个问题,采用了延迟删除的方式.在返回成功/失败的结果后10秒才删除缓存数据.
   ```
7. 感谢你看到这里,如有疑问或意见欢迎评论.
   ​																																													
