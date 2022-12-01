<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8" name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui" />

    <style type="text/css">
        .sky {
            width: 690px;
            height: 18px;
            font-size: 20px;
            font-weight: 500;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            border-spacing:0;
            border:solid #000 1px;
        }

        .num {
            width: 16px;
            height: 15px;
            font-size: 8px;
            font-weight: 400;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            border-spacing:0;
            border:solid #000 1px;
        }

        .goodsName {
            width: 32px;
            height: 11px;
            font-size: 10px;
            font-weight: 400;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            word-break: break-all;
            border-spacing:0;
            border:solid #000 1px;
        }

        .eName {
            width: 32px;
            height: 11px;
            font-size: 10px;
            font-weight: 400;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            word-break: break-all;
            border-spacing:0;
            border:solid #000 1px;
        }

        .mainImage {
            width: 16px;
            height: 11px;
            font-size: 10px;
            font-weight: 400;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            border-spacing:0;
            border:solid #000 1px;
        }

        .spe {
            width: 10px;
            height: 11px;
            font-size: 10px;
            font-weight: 400;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            word-break: break-all;
            border-spacing:0;
            border:solid #000 1px;
        }

        .box {
            width: 320px;
            height: 11px;
            font-size: 10px;
            font-weight: 400;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            word-break: break-all;
            border-spacing:0;
            border:solid #000 1px;
        }

        .pifs {
            width: 32px;
            height: 11px;
            font-size: 10px;
            font-weight: 400;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            word-break: break-all;
            border-spacing:0;
            border:solid #000 1px;
        }

        .jylsj {
            width: 32px;
            height: 11px;
            font-size: 10px;
            font-weight: 400;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            word-break: break-all;
            border-spacing:0;
            border:solid #000 1px;
        }

        .hdbz {
            width: 32px;
            height: 11px;
            font-size: 10px;
            font-weight: 400;
            line-height: 18px;
            color: #111111;
            opacity: 1;
            word-break: break-all;
            border-spacing:0;
            border:solid #000 1px;

        }



        .thclass {
            height: 50px;
        }
        .tab{
            border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;
        }
    </style>

</head>
<body >

<#-- table-layout: fixed 表格固定 -->

<table class="tab" width="690"  border=none cellspacing="0" align="center" opacity="0" style="font-family: SimSun; table-layout: auto; line-height:0">
    <tr>
        <th class="thclass" colspan="9" align="center" > <a class="sky">${arr.start}${arr.title}</a></th>
    </tr>
    <tr>
        <th width="10" class="thclass" align="center"><a class="num">&nbsp;序号&nbsp;</a></th>
        <th width="20" class="thclass" align="center"><a class="goodsName">商品名称</a></th>
        <th width="20" class="thclass" align="center"><a class="eName">英文名称</a></th>
        <th width="20" class="thclass" align="center"><a class="mainImage">主图</a></th>
        <th width="20" class="thclass" align="center"><a class="spe">规格</a></th>
        <th width="10" class="thclass" align="center"><a class="box">&nbsp;箱规&nbsp;</a></th>
        <th width="5" class="thclass" align="center"><a class="pifs">批发价</a></th>
        <th width="5" class="thclass" align="center"><a class="jylsj">零售价</a></th>
        <th width="20" class="thclass" align="center"><a class="hdbz">活动备注</a></th>
    </tr>

    <#list arr.data as it >
        <tr>
            <td align="center"><a class="num">${it_index+1}</a></td>
            <td ><a class="goodsName"> ${it.cnName} </a> </td>
            <td><a class="eName">${it.enName}  </a></td>
            <td align="center"><a class="mainImage"><img src="${it.image}" width="50" /></a></td>
            <td align="center"><a class="spe">${it.spec}</a></td>
            <td align="center"><a class="box">${it.boxSpec}</a></td>
            <td align="center"><a class="pifs">${it.wholesalePrice}</a></td>
            <td align="center"><a class="jylsj">${it.marketPirce}</a></td>
            <td><a class="hdbz">${it.remarks}</a></td>
        </tr>
    </#list>
    <tr >
        <th class="thclass" colspan="9" align="center" > <a class="sky"> 此报价单${arr.num}天内有效${arr.date} &nbsp; &nbsp; &nbsp;  报价人：${arr.nickName} </a></th>
    </tr>

</table>


</body>

</html>