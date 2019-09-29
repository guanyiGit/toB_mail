package com.utils;


import com.orgmanagement.domain.OrgInfoDO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class orgtills {




    /**
     * 根据当前组织查询父级组织
     */
    public static  List<OrgInfoDO> getFatherOrgsByOrgId(List<OrgInfoDO> list , int orgId, List<OrgInfoDO> clist){
        List<OrgInfoDO> orgList = dealOrg(list, orgId, clist);
        Collections.reverse(orgList);
        return orgList;
    }

    public static  List<OrgInfoDO> dealOrg(List<OrgInfoDO> list , int orgId,List<OrgInfoDO> clist){

        List<OrgInfoDO> sList = list.stream().filter(org -> { return orgId == org.getId(); }).collect(Collectors.toList());
        if(sList==null || sList.size() == 0){
            return clist;
        }
        OrgInfoDO self = sList.get(0);
        clist.add(self);
        getFatherOrgsByOrgId(list,self.getPid(),clist);
        return clist;
    }

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

    /**
     *
     * 查询当前部门下的所有部门
     */
    @SuppressWarnings("all")
    public static List<OrgInfoDO> getDeptsById(List<OrgInfoDO> list, int detpId,List<OrgInfoDO> clist){

        List<OrgInfoDO> collect = list.stream().filter(dept -> {
            return detpId == dept.getId();
        }).collect(Collectors.toList());
        if(collect.size()==0||collect==null){
            return clist;
        }
        OrgInfoDO deptDO = collect.get(0);
        clist.add(deptDO);

        List<OrgInfoDO> childrens = list.stream().filter(org -> {
            return deptDO.getId() == org.getPid();
        }).collect(Collectors.toList());
        if(childrens.size()==0||childrens==null){
            return clist;
        }
        childrens.stream().forEach(child ->{
            int orgId = child.getId();
            getDeptsById(list,orgId,clist);
        });
        return clist;
    }

    public static List<OrgInfoDO> findBottomOrgList(List<OrgInfoDO> list, int orgId,List<OrgInfoDO> clist){
        //获取所有当前组织下的所有子组织
        List<OrgInfoDO> children = list.stream().filter(org -> {
            return orgId == org.getPid();
        }).collect(Collectors.toList());

        if (children==null||children.size()==0){
            //如果没有子组织，则当前组织为最底层组织
            List<OrgInfoDO> collect = list.stream().filter(org -> {
                return orgId == org.getId();
            }).collect(Collectors.toList());
            clist.add(collect.get(0));
        }
        children.stream().forEach(child -> {
            findBottomOrgList(list,child.getId(),clist);
        });
        return clist;
    }

    /**
     * 查询出当前组织下的所有组织
     */

    public static List<OrgInfoDO> getChildren(List<OrgInfoDO> list, int detpId,List<OrgInfoDO> clist){
        List<OrgInfoDO> children = list.stream().filter(org -> {
            return detpId == org.getPid();
        }).collect(Collectors.toList());
        if(children==null||children.size()==0){
            return clist;
        }
        clist.addAll(children);
        children.stream().forEach(child->{
            getChildren(list,child.getId(),clist);
        });
        return clist;
    }


}
