/**
 * 
 */
package com.flipkart.service;

/**
 * @author Lenovo
 *
 */
public interface CourseCatalogInterface {
    // 	public course[]  getCourseCatalog(string department);
	public void  getCourseCatalog(String department);

 	// 	public course[]  getAllCourseCatalog();
 	public void  getAllCourseCatalog();
 	
 	public boolean  updateProfId(int courseID);
 	public boolean  updateIsOffered(int courseID);

}
