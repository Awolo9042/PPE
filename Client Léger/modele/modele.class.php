<?php
	//exécuter des requetes d'extraction / injection des données.
	class Modele {
		private $unPDO ; //instance de la classe PDO : PHP DATA Object 

		public function __construct($serveur, $bdd, $user, $mdp){
			$this->unPDO = null; 
			try{
				$url = "mysql:host=".$serveur.";dbname=".$bdd;
				//instanciation de la classe PDO 
				$this->unPDO = new PDO($url, $user, $mdp); 
			}
			catch(PDOException $exp){
				echo "<br/> Erreur de connexion à la BDD !";
				echo $exp->getMessage(); 
			}
		}
 
		// login
		public function verifConnexion ($email, $mdp)
		{
			if($this->unPDO != null){
				$requete ="select * from users where email =:email and 
				mdp = :mdp ;";
				$donnees=array(":email"=>$email, ":mdp"=>$mdp); 
				$select = $this->unPDO->prepare($requete); 
				$select->execute ($donnees); 
				$unUser = $select->fetch(); 
				return $unUser; 
			}else {
				return null; 
			}
		}

		// recup destinations

		public function	getDestinations()
		{
			if($this->unPDO != null){
				$requete ="select * from destinations;";
				$select = $this->unPDO->prepare($requete); 
				$select->execute (); 
				$lesDestinations = $select->fetchAll(); 
				return $lesDestinations; 
			}else {
				return null; 
			}
		}

		public function getTransports()
		{
			if($this->unPDO != null){
				$requete ="select * from transports;";
				$select = $this->unPDO->prepare($requete); 
				$select->execute (); 
				$lesTransports = $select->fetchAll(); 
				return $lesTransports; 
			}else {
				return null; 
			}
		}

		public function getLogements()
		{
			if($this->unPDO != null){
				$requete ="select * from logements;";
				$select = $this->unPDO->prepare($requete); 
				$select->execute (); 
				$lesLogements = $select->fetchAll(); 
				return $lesLogements; 
			}else {
				return null; 
			}
		}

		
		

		public function getDestinationName()
		{
			if(isset($_SESSION['destination'])) {
				$destination_id = $_SESSION['destination'];
				$requete ="SELECT destination_name FROM destinations WHERE destination_id = $destination_id";
				$select = $this->unPDO->prepare($requete); 
				$select->execute (); 
				$destination = $select->fetch(); 
				return $destination['destination_name']; 
			} else {
				return null; 
			}
		}	

		public function getDestinationPrix()
		{
			if(isset($_SESSION['destination'])) {
				$destination_id = $_SESSION['destination'];
				$requete ="SELECT destination_prix FROM destinations WHERE destination_id = $destination_id";
				$select = $this->unPDO->prepare($requete); 
				$select->execute (); 
				$destination = $select->fetch(); 
				return $destination['destination_prix']; 
			} else {
				return null; 
			}
		}
		
		public function getTransportName()
		{
			if(isset($_SESSION['transport'])) {
				$transport_id = $_SESSION['transport'];
				$requete ="SELECT transport_name FROM transports WHERE transport_id = $transport_id";
				$select = $this->unPDO->prepare($requete); 
				$select->execute (); 
				$transport = $select->fetch(); 
				return $transport['transport_name']; 
			} else {
				return null; 
			}
		}

		public function getTransportPrix()
		{
			if(isset($_SESSION['transport'])) {
				$transport_id = $_SESSION['transport'];
				$requete ="SELECT transport_prix FROM transports WHERE transport_id = $transport_id";
				$select = $this->unPDO->prepare($requete); 
				$select->execute (); 
				$transport = $select->fetch(); 
				return $transport['transport_prix']; 
			} else {
				return null; 
			}
		}

		public function getLogementName()
		{
			if(isset($_SESSION['logement'])) {
				$logement_id = $_SESSION['logement'];
				$requete ="SELECT logement_name FROM logements WHERE logement_id = $logement_id";
				$select = $this->unPDO->prepare($requete); 
				$select->execute (); 
				$logement = $select->fetch(); 
				return $logement['logement_name']; 
			} else {
				return null; 
			}
		}

		public function getLogementPrix()
		{
			if(isset($_SESSION['logement'])) {
				$logement_id = $_SESSION['logement'];
				$requete ="SELECT logement_prix FROM logements WHERE logement_id = $logement_id";
				$select = $this->unPDO->prepare($requete); 
				$select->execute (); 
				$logement = $select->fetch(); 
				return $logement['logement_prix']; 
			} else {
				return null; 
			}
		}

		public function createAccount($tab)
		{
			if ($this->unPDO != null) {
				$requete = "INSERT INTO users VALUES (:id_client, :genre, :nom, :prenom, :email, :telephone, :mdp, :role);";
				$hash = password_hash($tab['mdp'], PASSWORD_DEFAULT);
				$donnees = array(":id_client"=>NULL,
								 ":genre"=>$tab['genre'],
								 ":nom"=>$tab['nom'],
								 ":prenom"=>$tab['prenom'],
								 ":email"=>$tab['email'],
								 ":telephone"=>$tab['telephone'],
								 ":mdp"=>$hash,
								 ":role"=>"user");
				$insert = $this->unPDO->prepare($requete);
				$insert->execute($donnees);	  
			} else {
				return false;
			}
		}

		public function take_reservations($reservation_id, $destination_id, $date_depart, $date_retour, $nb_personnes, $logement_id, $transport_id, $reservations_prix, $id_client, $email)
		{
			if ($this->unPDO != null) {
				$requete = "INSERT INTO reservations (reservation_id, destination_id, date_depart, date_retour, nb_personnes, logement_id, transport_id, reservations_prix, id_client, email) 
							VALUES (:reservation_id, :destination_id, :date_depart, :date_retour, :nb_personnes, :logement_id, :transport_id, :reservations_prix, :id_client, :email)";
				$donnees = array(
					":reservation_id" => NULL,
					":destination_id" => $destination_id,
					":date_depart" => $date_depart,
					":date_retour" => $date_retour,
					":nb_personnes" => $nb_personnes,
					":logement_id" => $logement_id,
					":transport_id" => $transport_id,
					":reservations_prix" => $reservations_prix,
					":id_client" => $id_client,
					":email" => $email
				);
				$insert = $this->unPDO->prepare($requete);
				$insert->execute($donnees);
			} else {
				return false;
			}
		}
		
		
		public function checkLogin($email, $mdp)
		{
			
			if($this->unPDO != null){

					if(filter_var($email, FILTER_VALIDATE_EMAIL))
					{
						$requete = "select * from users where email = :email ";
						$select = $this->unPDO->prepare($requete); 
						$params = ['email'=>$email];
						$select->execute($params);
						if($select->rowCount() > 0)
						{
							$getRow = $select->fetch(PDO::FETCH_ASSOC);
							if(password_verify($mdp, $getRow['mdp']))
							{
								unset($getRow['mdp']);
								$_SESSION = $getRow;
								header('location:index.php?page=0');
								exit();
							}
							else
							{
								$errors[] = "erreur";
							}
						}
						else
						{
							$errors[] = "erreur";
						}
						
					}
					else
					{
						$errors[] = "Email invalide";	
					}
			
				}
				else
				{
					$errors[] = "Email et mot de passe requis.";	
				}
			
			}	
		

		
 } //fin de la classe
?>