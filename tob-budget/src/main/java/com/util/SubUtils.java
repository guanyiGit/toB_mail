package com.util;

import com.entities.TSubject;

import java.util.List;
import java.util.stream.Collectors;

public class SubUtils {

    /**
     * 获取当前科目下所有子科目(包含本身)
     * @param list
     * @param subId
     * @param clist
     * @return
     */
    public static List<TSubject> getSubById(List<TSubject> list,Integer subId,List<TSubject> clist){
        if (subId == 0){
            List<TSubject> childSubs = list.stream().filter(t -> {
                return t.getpId() ==subId;
            }).collect(Collectors.toList());
            if (childSubs.size() == 0 || childSubs == null){
                return  clist;
            }else {
                childSubs.stream().forEach(child ->{
                    getSubById(list,child.getId(),clist);
                });
            }
        }
        List<TSubject> collect = list.stream().filter(x -> {
            return x.getId() == subId;
        }).collect(Collectors.toList());
        if(collect.size()==0||collect==null){
            return clist;
        }
        TSubject subject = collect.get(0);
        clist.add(subject);

        List<TSubject> childSubs = list.stream().filter(t -> {
            return subject.getId() == t.getpId();
        }).collect(Collectors.toList());
       if (childSubs.size() == 0 || childSubs == null){
           return  clist;
       }
        childSubs.stream().forEach(child -> {
            getSubById(list,child.getId(),clist);
        });
        return clist;
    }

    /**
     * 获取当前科目下最底层子科目
     * @param list
     * @param subId
     * @param clist
     * @return
     */
    public static List<TSubject> getSubChildById(List<TSubject> list,Integer subId,List<TSubject> clist){
        List<TSubject> childSubs = list.stream().filter(x -> {
            return x.getpId() == subId;
        }).collect(Collectors.toList());
        if (childSubs.size() == 0 || childSubs == null){
            List<TSubject> collect = list.stream().filter(t -> {
                return t.getId() == subId;
            }).collect(Collectors.toList());
            clist.add(collect.get(0));
        }
        childSubs.stream().forEach( child -> {
            getSubChildById(list,child.getId(),clist);
        } );
        return clist;
    }



}
