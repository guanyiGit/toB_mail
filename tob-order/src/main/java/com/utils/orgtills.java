package com.utils;

//import com.entities.OrgInfo;
//import com.orgmanagement.domain.DeptDO;

//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;

import com.entities.OrgInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class orgtills {

//    /**
//     * 根据当前组织查询父级组织
//     */
//    public static  List<OrgInfo> getFatherOrgsByOrgId(List<OrgInfo> list , int orgId,List<OrgInfo> clist){
//        List<OrgInfo> orgList = dealOrg(list, orgId, clist);
//        Collections.reverse(orgList);
//        return orgList;
//    }
//
//    public static  List<OrgInfo> dealOrg(List<OrgInfo> list , int orgId,List<OrgInfo> clist){
//
//        List<OrgInfo> sList = list.stream().filter(org -> { return orgId == org.getId(); }).collect(Collectors.toList());
//        if(sList==null || sList.size() == 0){
//            return clist;
//        }
//        OrgInfo self = sList.get(0);
//        clist.add(self);
//        getFatherOrgsByOrgId(list,self.getPid(),clist);
//        return clist;
//    }
//
//    /**
//     *
//     * 查询当前部门下的所有部门
//     */
//    public static List<DeptDO> getDeptIdsById(List<DeptDO> list, Long detpId,List<DeptDO> clist){
//        List<DeptDO> collect = list.stream().filter(dept -> {
//            return detpId == dept.getDeptId();
//        }).collect(Collectors.toList());
//        if(collect.size()==0||collect==null){
//            return clist;
//        }
//        DeptDO deptDO = collect.get(0);
//        clist.add(deptDO);
//
//        List<DeptDO> childrens = list.stream().filter(org -> {
//            return deptDO.getDeptId() == org.getParentId();
//        }).collect(Collectors.toList());
//        if(childrens.size()==0||childrens==null){
//            return clist;
//        }
//        childrens.stream().forEach(child ->{
//            Long orgId = child.getDeptId();
//            getDeptIdsById(list,orgId,clist);
//        });
//        return clist;
//    }
//
//    /**
//     *
//     * 查询当前部门下的所有部门
//     */
//    public static List<OrgInfo> getDeptsById(List<OrgInfo> list, int detpId,List<OrgInfo> clist){
//
//        List<OrgInfo> collect = list.stream().filter(dept -> {
//            return detpId == dept.getId();
//        }).collect(Collectors.toList());
//        if(collect.size()==0||collect==null){
//            return clist;
//        }
//        OrgInfo deptDO = collect.get(0);
//        clist.add(deptDO);
//
//        List<OrgInfo> childrens = list.stream().filter(org -> {
//            return deptDO.getId() == org.getPid();
//        }).collect(Collectors.toList());
//        if(childrens.size()==0||childrens==null){
//            return clist;
//        }
//        childrens.stream().forEach(child ->{
//            int orgId = child.getId();
//            getDeptsById(list,orgId,clist);
//        });
//        return clist;
//    }
//
//    /**
//     * 查询出当前组织下的所有组织
//     */
//
//    public static List<OrgInfo> getChildren(List<OrgInfo> list, int detpId,List<OrgInfo> clist){
//        List<OrgInfo> children = list.stream().filter(org -> {
//            return detpId == org.getPid();
//        }).collect(Collectors.toList());
//        if(children==null||children.size()==0){
//            return clist;
//        }
//        clist.addAll(children);
//        children.stream().forEach(child->{
//            getChildren(list,child.getId(),clist);
//        });
//        return clist;
//    }
//
//
    public  static List<OrgInfo>getOgrInfoList(List<OrgInfo>orgInfoList,Integer orgId,List<OrgInfo>returnList){
        OrgInfo orgInfo=new OrgInfo();
        for (int i=0;i<orgInfoList.size();i++){//根据orgid得到对象
            if(orgInfoList.get(i).getId()==orgId){
                orgInfo=orgInfoList.get(i);
                break;
            }
        }
        returnList.add(orgInfo);
        List<OrgInfo>list=new ArrayList<>();
        for (int i=0;i<orgInfoList.size();i++){//得到子部门
            if(orgInfoList.get(i).getPid()==orgInfo.getId()){
                list.add(orgInfoList.get(i));
            }
        }
        if(list==null||list.size()==0){
            return returnList;//if没有子部门结束递归
        }
        for (int i=0;i<list.size();i++){
            getOgrInfoList(orgInfoList,list.get(i).getId(),returnList);
        }
        return returnList;
    }
    //得到技术编码
    public static String getTCode(String qd,Integer i){
        //code=19+qd+i++
        String date=(new Date()).toString();
        return date.substring(date.length() -2,date.length())+qd+(++i);
    }
    public static void main(String[]args){
        String date=(new Date()).toString();
        System.out.print(date.substring(date.length() -2,date.length()));;   //截掉
    }
}
