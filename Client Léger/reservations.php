<?php
// session_start();
require_once('controleur/config_bdd.php');
require_once("controleur/controleur.class.php"); 
//intancier ma classe Controleur 
$unControleur = new Controleur($serveur, $bdd, $user, $mdp);

if(isset($_SESSION['email'])) {
    $email = $_SESSION['email'];
    $sql = "SELECT reservations.*, destinations.destination_name, transports.transport_name, logements.logement_name
            FROM reservations
            INNER JOIN destinations ON reservations.destination_id = destinations.destination_id
            INNER JOIN transports ON reservations.transport_id = transports.transport_id
            INNER JOIN logements ON reservations.logement_id = logements.logement_id
            WHERE reservations.email = :email";

    $stmt = $pdo->prepare($sql);
    $stmt->bindParam(':email', $email);
    $stmt->execute();
    $reservations = $stmt->fetchAll();
} else {
    echo "Vous n'êtes pas connecté";
}

if(isset($_POST['submit'])) {
    $id = $_POST['id'];
    $sql = "DELETE FROM reservations WHERE reservation_id = :id";
    $stmt = $pdo->prepare($sql);
    $stmt->bindParam(':id', $id);
    $stmt->execute();
    $_SESSION['success'] = "La réservation a été annulée avec succès ! (pensez à actualiser la page)";
    // header('location: reservations.php');
}

?>
<center>

<h1> Mes Réservations </h1>

<!-- <br><br><br><br><br> -->

<table>
 <tr class="table-primary">
  <div class="container">
   <div class="row">
   
    <div class="col">
     <td class="tab">Destination</td>
    </div>

    <div class="col">
     <td class="tab">Moyen de Transport</td>
    </div>

    <div class="col">
     <td class="tab">Date de départ</td>
    </div>

    <div class="col">
     <td class="tab">Date de retour</td>
    </div>

    <div class="col">
     <td class="tab">Type de logement</td>
    </div>

    <div class="col">
     <td class="tab">Email de reservation</td>
    </div>

    <div class="col">
     <td class="tab">Montant total de la réservation</td>
    </div>

    <div class="col">
     <td class="tab">Annuler</td>
    </div>
   
   </div>
  </div>
 </tr>

 <?php foreach($reservations as $reservation): ?>

  <tr class="table-secondary">
   <div class="container">
    <div class="row">
     
     <div class="col">
      <td class="tab"><?= $reservation['destination_name'] ?></td>
     </div>

     <div class="col">
      <td class="tab"><?= $reservation['transport_name'] ?></td>
     </div>

     <div class="col">
      <td class="tab"><?= $reservation['date_depart'] ?></td>
     </div>

    <div class="col">
        <td class="tab"><?= $reservation['date_retour'] ?></td>
    </div>

     <div class="col">
      <td class="tab"><?= $reservation['logement_name'] ?></td>
     </div>

     <div class="col">
      <td class="tab"><?= $reservation['email'] ?></td>
     </div>

    <div class="col">
        <td class="tab"><?= $reservation['reservations_prix'] ?></td>
    </div>

     <div class="col">
      <td class="tab">
            <form action="" method="post">
                <input type="hidden" name="id" value="<?= $reservation['reservation_id'] ?>">
                <input type="submit" name="submit" value="Annuler la réservation">
            </form>  
    </td>
     </div>
    
    </div>
   </div>
  </tr>

 <?php endforeach; ?>
</table>

<!-- Afficher les notifications -->
<div class="notifications">
    <?php if(isset($_SESSION['success'])): ?>
        <div class="alert alert-success">
            <?= $_SESSION['success'] ?>
        </div>
        <?php unset($_SESSION['success']); ?>
    <?php endif; ?>

    <?php if(isset($_SESSION['error'])): ?>
        <div class="alert alert-danger">
            <?= $_SESSION['error'] ?>
        </div>
        <?php unset($_SESSION['error']); ?>
    <?php endif; ?>
</div>

</center>



<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
<link rel="stylesheet" href="css/reservations.css">


