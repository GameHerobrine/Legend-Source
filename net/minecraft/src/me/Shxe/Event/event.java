package net.minecraft.src.me.Shxe.Event;

public class event<T> {

	public boolean cancelled;
	public eventType type;
	public eventDirection direction;
	
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public eventType getType() {
		return type;
	}
	
	public void setType(eventType type) {
		this.type = type;
	}
	
	public eventDirection getDirection() {
		return direction;
	}
	
	public void setDirection(eventDirection direction) {
		this.direction = direction;
	}
	
	public boolean isPre() {
		if(type == null)
			return false;
		
		return type == eventType.PRE;
	}
	public boolean isPost() {
		if(type == null)
			return false;
		
		return type == eventType.POST;
	}
	
	

	
	public boolean isInComing() {
		if(direction == null)
			return false;
		
		return direction == eventDirection.INCOMING;
	}
	public boolean isOutGoing() {
		if(direction == null)
			return false;
		
		return direction == eventDirection.OUTGOING;
	}
	
}
