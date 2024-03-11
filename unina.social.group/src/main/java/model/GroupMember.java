package model;

public class GroupMember {
	
    private Group group;
    private User member;
    
    public GroupMember(Group group, User member) {
    	this.group = group;
    	this.member = member;
	}
    
	public Group getGroup() { return group; }
	public User getMember() { return member; }
	
	public void setGroup(Group group) { this.group = group; }
	public void setMember(User member) { this.member = member; }
}
