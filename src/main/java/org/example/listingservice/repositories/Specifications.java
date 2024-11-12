package org.example.listingservice.repositories;

import jakarta.persistence.criteria.Predicate;
import org.example.listingservice.builders.BuildingSearchBuilder;
import org.example.listingservice.models.Building;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

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
            query.orderBy(cb.desc(root.get("modifiedDate")));
            return pr;
        };
    }

    public static Specification<Building> getSomeRelateWithBuilding(Building b) {
        return (root, query, cb) -> {
            Predicate pr = cb.conjunction();
            if (b.getName() != null && !b.getName().isEmpty()) {
                pr = cb.or(pr, cb.like(root.get("name"),"%" + b.getName()+ "%" ));
            }
            if (b.getDistrict() != null && !b.getDistrict().isEmpty()) {
                pr = cb.or(pr, cb.equal(root.get("district"), b.getDistrict()));
            }
            if(b.getWard() != null && !b.getWard().isEmpty()){
                pr = cb.or(pr, cb.like(root.get("ward"), "%" + b.getWard() + "%"));
            }
            query.orderBy(cb.desc(root.get("modifiedDate")));
            return pr;
        };
    }

}
