package cn.edu.jxufe.controller;

import cn.edu.jxufe.service.GoodsInfoService;
import cn.edu.jxufe.service.MemberInfoService;
import com.aliyun.oss.OSSClient;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class UploadFileController {
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private MemberInfoService memberInfoService;
    @RequestMapping("uploadfile")
    public Object uploadFile(@RequestParam("fs") MultipartFile file,@RequestParam("title") String title,@RequestParam("subtitle") String subtitle,@RequestParam("author") String author){
        System.out.println("上传的图像名是"+file.getOriginalFilename());
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint="http://oss-cn-beijing.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，
        // 创建并使用RAM子账号进行API访问或日常运维，
        // 请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId="LTAI98muHU0MBzqi";
        String accessKeySecret="ulZlJQHRanJ1kHdxYVdUElW90OST7q";
        String buckName="shoppingimage";
        //创建oss存储对象的客户端对象
        OSSClient ossClient=new OSSClient(endpoint,accessKeyId,accessKeySecret);
        try {
            // 上传文件流。
            InputStream inputStream=file.getInputStream();
            ossClient.putObject(buckName, file.getOriginalFilename(), inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        String url= endpoint.replace("http://","http://"+buckName+".")+"/"+file.getOriginalFilename();

        System.out.println("输入的标题名是"+title);
        System.out.println("输入的副标题名是"+subtitle);
        System.out.println("输入的作者名是"+author);
        Integer mid=memberInfoService.getMId(author);//通过输入的会员名字得到相应的会员id
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date time=null;
        try {
            time= df.parse(df.format(new Date())); //将刚得到的时间字符串转换为Date类型
        } catch (ParseException e) {
            e.printStackTrace();
        }
        goodsInfoService.uploadGoods(title,subtitle,url,mid,time);
        return "upload_file";
    }


}


/**
 @RequestMapping("uploadtxt")
 @ResponseBody
 public Object uploadTxt(@RequestParam("title") String title,@RequestParam("subtitle") String subtitle,@RequestParam("author") String author){
 System.out.println("输入的标题名是"+title);
 System.out.println("输入的副标题名是"+subtitle);
 System.out.println("输入的作者名是"+author);
 int mid=memberInfoService.getMId(author);//通过输入的会员名字得到相应的会员id
 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
 java.util.Date time=null;
 try {
 time= df.parse(df.format(new Date())); //将刚得到的时间字符串转换为Date类型
 } catch (ParseException e) {
 e.printStackTrace();
 }
 goodsInfoService.uploadGoods(title,subtitle,,mid,time);
 return null;
 }

 //发布商品
 @RequestMapping(value = "uploadimage")
 @ResponseBody
 public Object uploadGImage(@RequestParam("fs") MultipartFile f,HttpServletRequest req){
 System.out.println("准备上传文件");
 System.out.println("输出上传文件的名字:"+f.getOriginalFilename());
 //获取服务器是upload文件夹绝对路径
 String path=req.getSession().getServletContext().getRealPath("upload/");
 System.out.println("站点的实际文件路径:"+path);
 String fileName= UUID.randomUUID().toString()+f.getOriginalFilename();
 try{
 FileOutputStream fou=new FileOutputStream(path+fileName);
 fou.write(f.getBytes());
 fou.close();
 return fileName;
 }catch (Exception ex){
 ex.printStackTrace();
 return null;
 }
 }
 @RequestMapping(value = "savetitle")
 @ResponseBody
 public Object saveTitle(@RequestParam("title") String title,@RequestParam("subtitle") String subtitle,@RequestParam("author") String author,@RequestParam("url") String url){
 System.out.println("准备存储到数据库");
 System.out.println("title-->"+title);
 System.out.println("subtitle-->"+subtitle);
 System.out.println("author-->"+author);
 System.out.println("url-->"+url);
 return "success";
 }
 */