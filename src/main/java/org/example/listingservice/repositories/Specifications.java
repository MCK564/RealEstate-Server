package org.example.listingservice.repositories;

import jakarta.persistence.criteria.Predicate;
import org.example.listingservice.builders.BuildingSearchBuilder;
import org.example.listingservice.models.Building;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Specifications {
    public static Specification<Building> searchProductByConditions(BuildingSearchBuilder b, List<String> types) {
        return (root, query, cb) -> {
            Predicate pr = cb.conjunction();
            if (b.getDistrict() != null) {
                pr = cb.and(pr, cb.equal(root.get("district"), b.getDistrict()));
            }
            if (b.getName() != null && !b.getName().isEmpty()) {
                pr = cb.and(pr, cb.like(root.get("name"), "%"+b.getName()+"%"));
            }
            if (b.getWard() != null && !b.getWard().isEmpty()) {
                pr = cb.and(pr, cb.like(root.get("ward"), b.getWard()));
            }
            if (b.getStreet() != null && !b.getStreet().isEmpty()) {
                pr = cb.and(pr, cb.like(root.get("street"), "%"+b.getStreet()+"%"));
            }
            if (b.getManagerName() != null && !b.getManagerName().isEmpty()) {
                pr = cb.and(pr, cb.like(root.get("managerName"), "%"+b.getManagerName()+"%"));
            }
            if (b.getNumberOfBasement() != null && b.getNumberOfBasement() != 0) {
                pr = cb.and(pr, cb.equal(root.get("numberOfBasement"), b.getNumberOfBasement()));
            }
            if (b.getFloorArea() != null && b.getFloorArea() != 0) {
                pr = cb.and(pr, cb.equal(root.get("floorArea"), b.getFloorArea()));
            }
            if (b.getManagerPhoneNumber() != null && !b.getManagerPhoneNumber().isEmpty()) {
                pr = cb.and(pr, cb.equal(root.get("managerPhoneNumber"), b.getManagerPhoneNumber()));
            }
            if (b.getOwnerName() != null && !b.getOwnerName().isEmpty()) {
                pr = cb.and(pr, cb.like(root.get("user").get("fullName"), "%"+b.getOwnerName()+"%"));
            }
            if (b.getRentAreaFrom() != null && b.getRentAreaFrom() != 0) {
                pr = cb.and(pr, cb.greaterThanOrEqualTo(root.join("rentAreas").get("value"), b.getRentAreaFrom()));
            }
            if (b.getRentAreaTo() != null && b.getRentAreaTo() != 0) {
                pr = cb.and(pr, cb.lessThanOrEqualTo(root.join("rentAreas").get("value"), b.getRentAreaTo()));
            }
            if (b.getRentPriceFrom() != null && b.getRentPriceFrom() != 0) {
                pr = cb.and(pr, cb.greaterThanOrEqualTo(root.get("rentPrice"), b.getRentPriceFrom()));
            }
            if (b.getRentPriceTo() != null && b.getRentPriceTo() != 0) {
                pr = cb.and(pr, cb.lessThanOrEqualTo(root.get("rentPrice"), b.getRentPriceTo()));
            }
            if (types!=null && !types.isEmpty()) {
                Predicate typePr = cb.disjunction();
                for (String type : types) {
                    typePr = cb.or(typePr, cb.like(root.get("type"), "%" + type + "%"));
                }
                pr = cb.and(pr, typePr);
            }
            Objects.requireNonNull(query).orderBy(cb.desc(root.get("modifiedDate")));
            return pr;
        };
    }

    public static Specification<Building> findBuildingByMultipleConditions(BuildingSearchBuilder b, List<String> type){
        return (root, query, criteriaBuilder) ->{
            Predicate predicate = criteriaBuilder.conjunction();

            for(Field field: b.getClass().getDeclaredFields()){
                String fieldName = field.getName();
                field.setAccessible(true);
                try{
                    Object fieldValue = field.get(b);
                    if(fieldValue != null){
                        List<String> splitCondition = Arrays.asList(fieldValue.toString().split("[, ]+"));
                        if(!fieldName.startsWith("rentArea")&&!fieldName.startsWith("rentPrice")) {
                            if (field.getType().equals(String.class)) {
                                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(fieldName), "%" + fieldValue + "%"));
                            } else if (field.getType().equals(Long.class) || field.getType().equals(Integer.class) || field.getType().equals(Double.class)) {
                                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(fieldName), fieldValue));
                            }
                        }
                    }
                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }

            predicate = specialFunction(criteriaBuilder, root, predicate, b);

            if (type != null && !type.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, root.get("type").in(type));
            }

            Objects.requireNonNull(query).orderBy(criteriaBuilder.desc(root.get("modifiedDate")));
            return predicate;
        };
    }
    private static Predicate specialFunction(jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder,
                                             jakarta.persistence.criteria.Root<Building> root,
                                             Predicate predicate,
                                             BuildingSearchBuilder b) {


        return predicate;
    }
}
