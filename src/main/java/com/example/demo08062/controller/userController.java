package com.example.demo08062.controller;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import ognl.Ognl;

@Controller
@RequestMapping("/update")
public class userController {
    private static final Logger log = LoggerFactory.getLogger(userController.class);
    private final ApplicationContext applicationContext;
    @Autowired
    public userController(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    @ResponseBody
    @PostMapping("/reflect")
    public Result updateByReflect(@RequestParam String className,@RequestParam String field,@RequestParam Object value,@RequestParam boolean isStatic)  {
       try{
        //获取类信息
        Class clazz = Class.forName(className);
        log.info("传入的参数为：{}, {}, {}, {}", className, field, value, isStatic);

        //获取字段信息
        Field targetField = clazz.getDeclaredField(field);
        // 设置private字段的访问权限为可访问
       targetField.setAccessible(true);

       //从IOC容器中获取bean
       Object targetObject=isStatic?null:applicationContext.getBean(clazz);

       //调用方法修改变量的值
       boolean flag= handleFieldUpdate(targetField,targetObject,value);
       log.info("修改修改后的对象信息为"+targetObject);
       return flag?Result.fail("字段是char类型的，value只能有一个字符"):Result.ok("修改成功:" + className + "." + field + " 已更新为 " + value);

       } catch (ClassNotFoundException e) {
           log.info("修改失败：找不到类！！",e);
           return Result.fail("修改失败:类未找到：" + e.getMessage());
       } catch (NoSuchFieldException e) {
           log.info("修改失败：属性找不到！！",e);
           return Result.fail("修改失败:属性未找到：" + e.getMessage());
       } catch (IllegalAccessException e) {
           log.info("修改失败：访问权限有问题！！",e);
           return Result.fail("修改失败:访问权限异常：" + e.getMessage());
       } catch (Exception e) {
           log.info("修改失败：发生了不清楚的错误！！",e);
           return Result.fail("失败:发生修改错误：" + e.getMessage());
       }
    }

    @ResponseBody
    @PostMapping("/ognl")
    public Result updateByOgnl(@RequestParam String className,@RequestParam String field,@RequestParam Object value,@RequestParam boolean isStatic)  {
        try{
            //获取类信息
            Class clazz = Class.forName(className);
            System.out.println("传入的参数为："+className+","+field+","+value+","+isStatic);

            //获取字段
            Field targetField = clazz.getDeclaredField(field);

            //从IOC容器中获取bean
            Object targetObject=isStatic?null:applicationContext.getBean(clazz);

            if(isStatic){
                //调用方法修改值
                boolean flag=handleFieldUpdate(targetField,targetObject,value);
                if(flag){
                    return Result.fail(" 字段是char类型的，value只能有一个字符 ");
                }
            }else {
                Class<?> fieldType = targetField.getType();// class java.lang.String
                String typeName = fieldType.getName();//java.lang.String
                log.info(field + "变量类型属于：" + typeName);
                switch (typeName) {
                    case "byte":
                    case "short":
                    case "int":
                    case "long":
                    case "float":
                    case "double":
                    case "boolean":
                    case "java.lang.String":
                        Ognl.setValue(field, null, targetObject, value);
                        break;
                    case "char":
                        if(((String)value).length()>1) {
                            log.info("该字段是char类型的，只能输入一个字符！");
                            return Result.ok("该字段是char类型的，只能输入一个字符！");
                        }else {
                            log.info("It's a char.");
                            Ognl.setValue(field, null, targetObject, ((String) value).charAt(0));
                        }
                        break;
                    default:
                        log.info("It's not a recognized data type.");
                }
                log.info("修改后的对象为："+targetObject);
            }
            return  Result.ok("修改成功:" + className + "." + field + " 已更新为 " + value) ;
        } catch (ClassNotFoundException e) {
            log.info("修改失败:找不到类！！",e);
            return Result.fail("类未找到：" + e.getMessage());
        } catch (NoSuchFieldException e) {
            log.info("修改失败:属性找不到！！",e);
            return Result.fail("属性未找到：" + e.getMessage());
        } catch (Exception e) {
            log.info("修改失败:发生了不清楚的错误！！",e);
            return Result.fail("发生错误：" + e.getMessage());
        }
    }

    //具体的更新值的操作
    private boolean handleFieldUpdate(Field field,Object targetObject,Object value) throws IllegalAccessException {

        Class<?> fieldType = field.getType();// class java.lang.String
        String typeName = fieldType.getName();//java.lang.String
        System.out.println(field+"变量类型属于：" + typeName);

        switch (typeName) {
            case "byte":
                log.info("It's a byte.");
                field.set(targetObject, Byte.parseByte(value.toString()));
                break;
            case "short":
                log.info("It's a short.");
                field.set(targetObject, Short.parseShort(value.toString()));
                break;
            case "int":
                log.info("It's an int.");
                field.set(targetObject, Integer.parseInt(value.toString()));
                break;
            case "long":
                log.info("It's a long.");
                field.set(targetObject, Long.parseLong(value.toString()));
                break;
            case "float":
                log.info("It's a float.");
                field.set(targetObject, Float.parseFloat(value.toString()));
                break;
            case "double":
                log.info("It's a double.");
                field.set(targetObject, Double.parseDouble(value.toString()));
                break;
            case "char":
                if(((String)value).length()>1){
                    log.info("该字段是char类型的，只能输入一个字符！");
                    return true;
                }else {
                    log.info("It's a char.");
                    field.set(targetObject, ((String) value).charAt(0));
                }
                break;
            case "boolean":
                log.info("It's a boolean.");
                field.set(targetObject, Boolean.parseBoolean((String) value));
                break;
            case "java.lang.String":
                log.info("It's a String.");
                field.set(targetObject, value);
                break;
            default:
                log.info("It's not a recognized data type.");
        }
        return false;
    }
}