<?php
    require_once 'connection.php';
    header('Content-Type: application/json');


        $Uid = $_GET['id'];
        $query = "SELECT ListItem
                  FROM list_data
                  WHERE listID = $Uid;";

        $result = mysqli_query($connection, $query);


        while($row = mysqli_fetch_assoc($result)){
        
            $array[] = $row;
        }
        echo json_encode($array);