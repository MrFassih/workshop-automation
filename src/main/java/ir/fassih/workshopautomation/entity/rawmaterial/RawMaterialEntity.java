package ir.fassih.workshopautomation.entity.rawmaterial;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import groovy.transform.EqualsAndHashCode;
import ir.fassih.workshopautomation.entity.core.AbstractBaseEntity;
import ir.fassih.workshopautomation.entity.core.LogicallyDeletable;
import ir.fassih.workshopautomation.entity.rawmaterialcategory.RawMaterialCategoryEntity;
import lombok.Data;

@Data
@Table(name="DASH_RAW_MATERIAL")
@MappedSuperclass
@EqualsAndHashCode(callSuper=true)
public class RawMaterialEntity extends AbstractBaseEntity 
								implements LogicallyDeletable {

	@Column(name="TITLE")
	private String title;
	
	@Column(name="DELETED")
	private boolean deleted = false;
	
	@Column(name="UNIT_PRICE")
	private Long unitPrice;
	
	private RawMaterialCategoryEntity category;
}
