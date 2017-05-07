<?php
    require_once 'connection.php';
    header('Content-Type: application/json');


        $Uid = $_GET['id'];
        $query = "SELECT list.`listID`, list.`ListName`, list.`CreationDate`
                  FROM group_list INNER JOIN list ON group_list.`ListID` = list.`ListID`
                  WHERE group_list.`GroupID` = $Uid;";

        $result = mysqli_query($connection, $query);


        while($row = mysqli_fetch_assoc($result)){
        
            $array[] = $row;
        }
        echo json_encode($array);