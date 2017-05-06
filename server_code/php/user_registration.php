<?php

require_once 'sanitization.php';
header('Content-Type: application/json');

if(isset($_POST['NewEmail']) && isset($_POST['NewPassword']) && isset($_POST['FName']) && isset($_POST['LName'])){

    $email = sanitizeMYSQL($connection, $_POST['NewEmail']);
    $epassword = md5(sanitizeMYSQL($connection, $_POST['NewPassword']));
    $firstName = sanitizeMYSQL($connection, $_POST['FName']);
    $lastName = sanitizeMYSQL($connection, $_POST['LName']);


    //Until a better way is found, check to see if the account exist by querying
    $query = "SELECT * FROM user WHERE Email = '$email';";
    $result = mysqli_query($connection, $query);

    if(mysqli_num_rows($result) > 0){
        $json['failure'] = "That account already exists!";
        echo json_encode($json);
    }

    else {
        $query = "INSERT INTO `user` (`Email`, `Password`, `FName`, `LName`) VALUES ('$email', '$epassword', '$firstName', '$lastName' );";
        $result = mysqli_query($connection, $query);
        if($result){
            $json['success'] = "Account Created!";
            echo json_encode($json);
        }
    }

}


?>

