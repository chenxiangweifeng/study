package com.example.study.core.service.jpa;

import com.example.study.core.model.dto.Node;
import com.example.study.core.model.entity.Area;
import com.example.study.core.model.entity.City;
import com.example.study.core.model.entity.Province;
import com.example.study.core.repository.jpa.AreaRepository;
import com.example.study.core.repository.jpa.CityRepository;
import com.example.study.core.repository.jpa.ProvinceRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * tree
 */
@Service
public class TreeService {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AreaRepository areaRepository;

    public  List<Node> getChinaProvinceCities(){
        List<Node> result = new ArrayList<>();

        List<Province> provinceList = provinceRepository.findAll();
        List<City> cityList = cityRepository.findAll();
        List<Area> areaList = areaRepository.findAll();

        for (Province province : provinceList){
           Node node = new Node();
           result.add(node);
           node.setId(province.getId());
           node.setTitle(province.getProvince());
           String provinceId = province.getProvinceId();
           List<City> provCityList = cityList.stream().filter(x -> x.getProvinceId().equals(provinceId))
                                     .collect(Collectors.toList());
           if(CollectionUtils.isNotEmpty(provCityList)){
//               该省份下的城市不为空
               List<Node> provinceChildrens = new ArrayList<>();
               node.setChildren(provinceChildrens);
               for (City city: provCityList){
                   Node cityNode = new Node();
                   provinceChildrens.add(cityNode);
                   cityNode.setId(city.getId());
                   cityNode.setTitle(city.getCity());
                   List<Area> cityAreas = areaList.stream().filter(e -> e.getCityid().equals(city.getCityid()))
                           .collect(Collectors.toList());
                   if(CollectionUtils.isNotEmpty(cityAreas)){
                       List<Node> cityChildrens = new ArrayList<>();
                       cityNode.setChildren(cityChildrens);
                     for (Area area: cityAreas){
                         Node areaNode = new Node();
                         cityChildrens.add(areaNode);
                         areaNode.setId(area.getAreaid());
                         areaNode.setTitle(area.getArea());
                     }
                   }
               }
           }
       }
//        封装进入中国这个节点中
       List<Node> chinaTreeList = new ArrayList<>();
        Node chinaNode = new Node();
        chinaTreeList.add(chinaNode);
        chinaNode.setId("0");
        chinaNode.setTitle("中国");
        chinaNode.setChildren(result);

       return chinaTreeList;
    }


}
