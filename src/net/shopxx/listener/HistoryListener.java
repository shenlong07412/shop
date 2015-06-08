package net.shopxx.listener;

import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.springframework.stereotype.Component;



/**
 * 数据审核日志
 * @author
 *
 * 2014-7-15
 */
@Component("historyListener")
public class HistoryListener implements PostInsertEventListener,   
PostUpdateEventListener, PostDeleteEventListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void onPostDelete(PostDeleteEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPostUpdate(PostUpdateEvent arg0) {
		System.out.println("sd");
		
	}

	@Override
	public void onPostInsert(PostInsertEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
    
}
