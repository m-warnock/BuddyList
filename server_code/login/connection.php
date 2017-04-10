<?php 

  $db_hostname = 'localhost';
  $db_username = 'id1334313_admin';
  $db_password = 'warnock1';
  $db_database = 'id1334313_buddylist';
  

 $connection = mysqli_connect($db_hostname, $db_username,$db_password,$db_database);
 
 if (!$connection)
    die("Unable to connect to MySQL: " . mysqli_connect_errno());


?>
