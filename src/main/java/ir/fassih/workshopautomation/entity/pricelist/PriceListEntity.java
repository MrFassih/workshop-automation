package ir.fassih.workshopautomation.entity.pricelist;

import ir.fassih.workshopautomation.entity.core.Traceable;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "DASH_PRICE_LIST")
@Entity
public class PriceListEntity implements Traceable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Lob
    @Column(name="CONTENT")
    private byte[] content;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "LAST_MODIFICATION_DATE")
    private Date lastModificationDate;


}