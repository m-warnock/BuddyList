<?php
    require_once 'connection.php';
    header('Content-Type: application/json');


        $Uid = $_GET['id'];
        $query = "SELECT buddylist_group.`GroupID`, buddylist_group.`GroupName`, buddylist_group.`GroupDescription`,
                  buddylist_group.`GroupLeader`, buddylist_group.`CreationDate`
                  FROM member_of INNER JOIN buddylist_group ON member_of.`GroupID` = buddylist_group.`GroupID`
                  WHERE member_of.`UserID` = $Uid;";

        $result = mysqli_query($connection, $query);


        while($row = mysqli_fetch_assoc($result)){
        
            $array[] = $row;
        }
        echo json_encode($array);


?>