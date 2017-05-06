<?php
    require_once 'connection.php';
    header('Content-Type: application/json');

    if(isset($_POST['Id'])){
        $Uid = $_POST['Id'];
        $query = "SELECT buddylist_group.`GroupID`, buddylist_group.`GroupName`, buddylist_group.`GroupDescription`,
                  buddylist_group.`GroupLeader`, buddylist_group.`CreationDate`
                  FROM member_of INNER JOIN buddylist_group ON member_of.`GroupID` = buddylist_group.`GroupID`
                  WHERE member_of.`UserID` = $Uid;";
        $result = mysqli_query($connection, $query);

        $group_data_array = array();
        while($row = mysqli_fetch_assoc($result)){
            $resultrow = array('GroupID'=>$row['GroupID'],
                               'GroupName'=>$row['GroupName'],
                               'GroupDescription'=>$row['GroupDescription'],
                               'GroupLeader'=>$row['GroupLeader'],
                               'CreationDate'=>$row['CreationDate']);
            $group_data_array[]=$resultrow;
        }
        echo json_encode($group_data_array);
    }

?>