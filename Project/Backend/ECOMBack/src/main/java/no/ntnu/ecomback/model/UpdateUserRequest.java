/**

 UpdateUserRequest is a class that represents a request to update a user's information.
 */
package no.ntnu.ecomback.model;


import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateUserRequest {

    /**

     The user's email.
     */
    @Schema(description = "The email address of the user")
    private String email;
    /**

     The user's first name.
     */
    @Schema(description = "The updated first name of the user")
    private String firstName;
    /**

     The user's last name.
     */
    @Schema(description = "The updated last name of the user")
    private String lastName;

    /**
     * The user's username
     */
    @Schema(description = "The updated username of the user")
    private String username;
    /**

     The user's new password.
     */
    @Schema(description = "The updated password of the user")
    private String newPassword;
    /**

     The user's current password.
     */
    @Schema(description = "The current password of the user")
    private String currentPassword;


    /**
     Constructs a new UpdateUserRequest object with the specified parameters.
     @param email the user's email
     @param firstName the user's first name
     @param lastName the user's last name
     @param username the user's username
     @param newPassword the user's new password
     @param currentPassword the user's current password
     */
    public UpdateUserRequest(String email, String firstName, String lastName, String username, String newPassword, String currentPassword) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.newPassword = newPassword;
        this.currentPassword = currentPassword;
    }

    /**
     Returns the user's email.
     @return the user's email
     */
    public String getEmail() {
        return email;
    }
    /**

     Returns the user's first name.
     @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**

     Returns the user's last name.
     @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**

     Returns the user's username.
     @return the user's username

     */
    public String getUsername() {
        return username;
    }

    /**

     Returns the user's new password.
     @return the user's new password
     */
    public String getNewPassword() {
        return newPassword;
    }
    /**

     Returns the user's current password.
     @return the user's current password
     */
    public String getCurrentPassword() {
        return currentPassword;
    }
}