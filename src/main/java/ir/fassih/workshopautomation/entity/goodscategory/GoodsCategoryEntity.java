package ir.fassih.workshopautomation.entity.goodscategory;

import javax.persistence.*;

import ir.fassih.workshopautomation.entity.core.LogicallyDeletable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Table(name = "DASH_GOODS_CATEGORY")
@Entity
public class GoodsCategoryEntity implements LogicallyDeletable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DELETED")
    private Boolean deleted = false;

}
