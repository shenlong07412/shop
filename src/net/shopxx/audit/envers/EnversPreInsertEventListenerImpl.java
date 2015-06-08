package net.shopxx.audit.envers;

import org.hibernate.envers.configuration.AuditConfiguration;
import org.hibernate.envers.event.BaseEnversEventListener;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnversPreInsertEventListenerImpl extends BaseEnversEventListener implements PreInsertEventListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(EnversPreInsertEventListenerImpl.class);

    public EnversPreInsertEventListenerImpl(AuditConfiguration enversConfiguration) {
        super(enversConfiguration);
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        String entityName = event.getPersister().getEntityName();
        if (getAuditConfiguration().getEntCfg().isVersioned(entityName)) {
            logger.debug("Envers onPreInsert event.getEntity()={}", event.getEntity());
        }

        return false;
    }
}
