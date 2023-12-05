/*
 * Name        : User_info
 * Author      : [kang min jae]
 * Date        : 2023-12-05
 * Arguments   : int id (user's ID from User class), String profileImage, String intro, String job, String email, String mobile
 * Return      : User_info object
 * Description : This class represents additional information for a user. It stores details like profile image, introduction, job, email, and mobile number.
 *               It includes getters and setters for these properties. This class enhances the User entity by providing more personal and contact information.
 */

package entity;


public class UserInfo
{
    private final int id; // id from Userclass
    private String profileImage;
    private String intro;
    private String job;
    private String email;
    private String mobile;

    public UserInfo(int id, String profileImage, String intro, String job, String email, String mobile) {
        this.id = id;
        this.profileImage = profileImage;
        this.intro = intro;
        this.job = job;
        this.email = email;
        this.mobile = mobile;
    }

    // Get
    public int getId() {
        return id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getIntro() {
        return intro;
    }

    public String getJob() {
        return job;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    // Set
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}