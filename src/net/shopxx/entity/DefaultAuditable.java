package net.shopxx.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.domain.Persistable;

public interface DefaultAuditable<U, ID extends Serializable> extends Persistable<ID> {

    /**
     * Returns the user who created this entity.
     * 
     * @return the createdBy
     */
    U getCreateby();

    /**
     * Sets the user who created this entity.
     * 
     * @param createdBy the creating entity to set
     */
    void setCreateby(final U createdBy);

    /**
     * Returns the creation date of the entity.
     * 
     * @return the createdDate
     */
    Date getCreateDate();

    /**
     * Sets the creation date of the entity.
     * 
     * @param creationDate the creation date to set
     */
    void setCreateDate(final Date creationDate);

    /**
     * Returns the user who modified the entity lastly.
     * 
     * @return the lastModifiedBy
     */
    U getUpdateby();

    /**
     * Sets the user who modified the entity lastly.
     * 
     * @param lastModifiedBy the last modifying entity to set
     */
    void setUpdateby(final U lastModifiedBy);

    /**
     * Returns the date of the last modification.
     * 
     * @return the lastModifiedDate
     */
    Date getModifyDate();

    /**
     * Sets the date of the last modification.
     * 
     * @param lastModifiedDate the date of the last modification to set
     */
    void setModifyDate(final Date lastModifiedDate);
}
