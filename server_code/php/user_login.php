<?php
//This is the server side code that validates a user in the database
require_once "sanitization.php";
header('Content-Type: application/json');

if (isset($_POST['Email']) && isset($_POST['Password'])) {
    
    $email = sanitizeMYSQL($connection,$_POST['Email']); //sanitize the username
    $password = sanitizeMYSQL($connection,$_POST['Password']); //sanitize the password
    
    if(!empty($email) && !empty($password)){
        
        $encryptedPassword = md5($password);
        $query = "SELECT Id, Email, FName, LName FROM `user` WHERE Email = '$email' AND Password='$encryptedPassword' ";
        $result = mysqli_query($connection,$query);
        
        if (mysqli_num_rows($result) > 0) {
            $row = mysqli_fetch_assoc($result);
            $json['success'] = array(
                'id'=>$row['Id'],
                'email'=>$row['Email'],
                'fname'=>$row['FName'],
                'lname'=>$row['LName']);
            echo json_encode($json);
        }
        else{
            $json['failure'] = 'Invalid Username or Password.';
            echo json_encode($json);
        }
        mysqli_close($connection);
    }
    else{
        if(empty($email)){
            $json['failure'] = 'You must enter your email address.';
            echo json_encode($json);
        }
        else {
            $json['failure'] = 'You must enter your Password.';
            echo json_encode($json);
        }
    }
}

?>