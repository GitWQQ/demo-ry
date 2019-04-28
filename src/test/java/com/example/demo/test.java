package com.example.demo;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

public class test {
    public static void main(String[] args){
                                      //假设向数据库存入加密的对象
        UserDto user=new UserDto();   //创建一个对象
        user.setPassword("123456");   //模拟密码123456
        user.setSalt(ShiroKit.getRandomSalt());   //获取5位数的盐
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));   //把盐与密码传入方法中进行 md5加密方式 的1024次加密  最后得出加密密码
        System.out.println("密码："+user.getPassword()+"     "+"Salt："+ user.getSalt());  //打印  加密后的密码   与   盐的值
//最后把对象存入数据库中，小编看的guns项目以用户名不重复才可存入数据库

//模拟密码
        String str=new String();
        str="123456";   //模拟密码为123456
        //封装请求账号密码为shiro可验证的token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("huasheng", str.toCharArray());   //"huasheng"为登录时输入的用户名，这里直接写了字符串

        //获取数据库中的账号密码，准备比对   查找用户名所在的用户数据  这里直接用上面定义的user进行测试
//        User user = userMapper.getByAccount(username);

        String credentials = user.getPassword();//获取本账号加密过的密码
        String salt = user.getSalt();           //获取本账号中对应盐值
        ByteSource credentialsSalt = new Md5Hash(salt); //放入盐值
        System.out.println("credentialsSaltgetBytes"+credentialsSalt.getBytes());
        System.out.println("credentialsSaltgetClass"+credentialsSalt.getClass());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                new ShiroUser(), credentials, credentialsSalt, "");//第一个参数是对象，密码，ByteSource对象，realmName

        //校验用户账号密码
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);//MD5
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);//1024
        boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch( usernamePasswordToken, simpleAuthenticationInfo);//验证

        if (passwordTrueFlag) {
//            HashMap<String, Object> result = new HashMap<>();
//            result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getId())));
//            return result;
            System.out.println("登陆成功");
        } else {
//            return new ErrorResponseData(500, "账号密码错误！");
            System.out.println("账号密码错误！");
        }
    }
}

