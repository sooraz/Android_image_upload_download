 <?php
$servername = "localhost";
$username = "id8154736_root";
$password = "123456789";
// Create connection
$conn = new mysqli($servername, $username, $password, "id8154736_test");
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


 //echo "sooraz0";
    if (isset($_POST['name']) && isset($_POST['image'])) {
    
    
   //echo "sooraz1";
    $name = $_POST["name"]; //within square bracket should be same as Utils.imageName & Utils.image
   
    $image = $_POST["image"];
 
    $response = array();
 //echo "sooraz2";
    $decodedImage = base64_decode("$image");
 
    $return = file_put_contents("uploadedFiles/".$name.".JPG", $decodedImage);
//  echo "sooraz3";
    if($return !== false){
        $response['success'] = 1;
        $response['message'] = "Image Uploaded Successfully";
    }else{
        $response['success'] = 0;
        $response['message'] = "Image Uploaded Failed";
    }
    // echo "sooraz4";
    echo json_encode($response);

}








    
    $conn->close();


?>
