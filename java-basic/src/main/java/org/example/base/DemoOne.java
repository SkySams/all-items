package org.example.base;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zyh
 * @date: 2022/10/10
 */
public class DemoOne {


    @Test
    public void strng(){
//        StringBuilder type = new StringBuilder();
//        System.out.println(StrUtil.isBlank(type.toString()));

//        System.out.println(Runtime.getRuntime().availableProcessors());

        List<String> st = Arrays.asList("a","f");
       Iterator ier = st.iterator();
       if (ier.hasNext()){
           System.out.println(ier.next());
           while (ier.hasNext()){
               System.out.println(ier.next());
           }
       }

    }


    @Test
    public void main() {

        System.out.println(Math.max(1,2));


        String goodsPromotionType = "";
        goodsPromotionType = goodsPromotionType.replace("5","");
        goodsPromotionType = goodsPromotionType.replace("6","");
        goodsPromotionType = goodsPromotionType.replace("7","");
        System.out.println(goodsPromotionType);

        //问题现象
        Boolean condition = Boolean.FALSE;
        Double a = 0.1d;
        Double b = 0.2d;
        Double c = null;
//        Double result = condition? a*b:c;

        //尽量使用基本数据类型，避免包装数据类型的拆装包
        boolean condi = Boolean.FALSE;
        double a1 = 1d;
        double b1 = 2d;
        double c2 = 3d;

        double res = condi? a1 * b1 : c2;



//        Calendar calendar = Calendar.getInstance();
//        // 时
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        // 分
//        calendar.set(Calendar.MINUTE, 0);
//        // 秒
//        calendar.set(Calendar.SECOND, 0);
//        // 毫秒
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        Date time = calendar.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        String format = df.format(time);
//        System.out.println(format);
    }

    /** 构建菜单树函数 */
    public static List<MenuVO> buildMenuTree(List<MenuDO> menuList) {
        // 检查列表为空
//        if (CollectionUtils.isEmpty(menuList)) {
//            return Collections.emptyList();
//        }
        // 依次处理菜单
        int menuSize = menuList.size();
        List<MenuVO> rootList = new ArrayList<>(menuSize);
        Map<Long, MenuVO> menuMap = new HashMap<>(menuSize);
        for (MenuDO menuDO : menuList) {
            // 赋值菜单对象
            Long menuId = menuDO.getId();
            MenuVO menu = menuMap.get(menuId);
            if (Objects.isNull(menu)) {
                menu = new MenuVO();
                menu.setChildList(new ArrayList<>());
                menuMap.put(menuId, menu);
            }
            menu.setId(menuDO.getId());
            menu.setName(menuDO.getName());
            menu.setUrl(menuDO.getUrl());
            // 根据父标识处理
            Long parentId = menuDO.getParentId();
            if (Objects.nonNull(parentId)) {
                // 构建父菜单对象
                MenuVO parentMenu = menuMap.get(parentId);
                if (Objects.isNull(parentMenu)) {
                    parentMenu = new MenuVO();
                    parentMenu.setId(parentId);
                    parentMenu.setChildList(new ArrayList<>());
                    menuMap.put(parentId, parentMenu);
                }

                // 添加子菜单对象
                parentMenu.getChildList().add(menu);
            } else {
                // 添加根菜单对象
                rootList.add(menu);
            }
        }
        // 返回根菜单列表
        return rootList;
    }

    /** 菜单 DO 类 */
    @Setter
    @Getter
    @ToString
    public static class MenuDO {
        /** 菜单标识 */
        private Long id;
        /** 菜单父标识 */
        private Long parentId;
        /** 菜单名称 */
        private String name;
        /** 菜单链接 */
        private String url;
    }

    @Setter
    @Getter
    @ToString
    public static class MenuVO {
        /** 菜单标识 */
        private Long id;
        /** 菜单名称 */
        private String name;
        /** 菜单链接 */
        private String url;
        /** 子菜单列表 */
        private List<MenuVO> childList;
    }



}
