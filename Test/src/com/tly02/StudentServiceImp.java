package com.tly02;

@Component
public class StudentServiceImp implements IStudentService{
    
	
	@Resource("studentDAOImp")
	private IStudentDAO sd;
	@Override
	public void save() {
      sd.save();		
	}

}
