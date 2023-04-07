drop DATABASE if exists travelin_Event;

create DATABASE travelin_Event;

use travelin_Event;

CREATE TABLE users (
  id_client int(11) NOT NULL AUTO_INCREMENT,
  nom varchar(255) NOT NULL,
  prenom varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  telephone int(20) NOT NULL,
  mdp varchar(255) NOT NULL,
  role_client varchar(255) NOT NULL,
  statut varchar(255) NOT NULL,
  siret varchar(255) NOT NULL,
  PRIMARY KEY (id_client),
  UNIQUE KEY id_client (id_client)
);

CREATE TABLE destinations (
    destination_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    destination_name VARCHAR(255) NOT NULL,
    destination_prix INT(5) NOT NULL,
    destination_description TEXT NOT NULL
);

CREATE TABLE transports (
    transport_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    transport_name VARCHAR(255) NOT NULL,
    transport_prix INT(5) NOT NULL
);

CREATE TABLE logements (
    logement_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    logement_name VARCHAR(255) NOT NULL,
    logement_prix INT(5) NOT NULL
);

CREATE TABLE offres (
    offre_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    destination_id INT NOT NULL,
    transport_id INT NOT NULL,
    logement_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (destination_id) REFERENCES destinations(destination_id),
    FOREIGN KEY (transport_id) REFERENCES transports(transport_id),
    FOREIGN KEY (logement_id) REFERENCES types_logement(logement_id)
);

CREATE TABLE reservations (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    destination_id INT NOT NULL,
    date_depart VARCHAR (255)NOT NULL,
    date_retour VARCHAR (255) NOT NULL,
    nb_personnes INT NOT NULL,
    logement_id INT NOT NULL,
    transport_id INT NOT NULL,
    reservations_prix INT(10) NOT NULL, -- trouver un multi pour le global prix -> (destination + logement + transport) x nb_personnes (500 + 250 + 100) x 2 = 1700
    id_client INT(11) NOT NULL,
    email VARCHAR(200) NOT NULL,
    FOREIGN KEY (id_client) REFERENCES users(id_client),
    FOREIGN KEY (email) REFERENCES users(email),
    FOREIGN KEY (destination_id) REFERENCES destinations(destination_id),
    FOREIGN KEY (transport_id) REFERENCES transports(transport_id),
    FOREIGN KEY (logement_id) REFERENCES logements(logement_id)
);

CREATE TABLE promotions (
    promotion_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    offre_id INT NOT NULL,
    date_debut VARCHAR (255) NOT NULL,
    date_fin VARCHAR (255) NOT NULL,
    pourcentage DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (offre_id) REFERENCES offres(offre_id)
);

CREATE TABLE avis (
    avis_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    offre_id INT NOT NULL,
    id_client INT(6) NOT NULL,
    email VARCHAR(200) NOT NULL,
    note DECIMAL(10,2) NOT NULL,
    commentaire TEXT NOT NULL,
    FOREIGN KEY (id_client) REFERENCES users(id_client),
    FOREIGN KEY (email) REFERENCES users(email),
    FOREIGN KEY (offre_id) REFERENCES offres(offre_id)
);

CREATE TABLE img (
    img_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    img_category VARCHAR(255) NOT NULL,
    img_name VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL
);

CREATE TABLE nb_personnes (
    nb_personnes_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE,
    nb_personnes VARCHAR(255) NOT NULL
);

-- INSERT INTO données BDD

INSERT INTO destinations (destination_id, destination_name, destination_prix, destination_description) VALUES 
    (1, 'Londres', '200', 'Londres, la capitale de l\’Angleterre et du Royaume-Uni, est une grande ville du monde occidental, située sur la Tamise, à environ 50 km de la mer du Nord. Elle est connue pour ses musées, ses monuments historiques et ses parcs. Le musée national de Londres, le British Museum, abrite des collections de plus de 8 millions d\’objets, dont des œuvres d\’art et des antiquités égyptiennes. Le palais de Westminster, siège du Parlement britannique, abrite la Big Ben, une horloge à cloche de 96 mètres de haut. Le London Eye, une grande roue de 135 mètres de haut, offre une vue panoramique de la ville.'),
    (2, 'Rome', '300', 'Rome, capital de l\'Italie, elle est une grande ville historique qui regorge de monuments antiques et de sites religieux. La ville est connue pour ses ruines romaines, notamment le Colisée, un amphithéâtre colossal construit au 1er siècle après J.-C. et le Forum romain, un vaste complexe de temples, de théâtres et de monuments publics. La place Saint-Pierre, dans le Vatican, est le siège de l\’Église catholique et abrite la basilique du même nom.'),
    (3, 'Tokyo', '1200', 'Capitale animée du Japon, Tokyo associe les styles ultramodernes et traditionnels, dans un mélange de gratte-ciel aux néons lumineux et de temples anciens. Le somptueux Meiji-jingū est un sanctuaire shinto réputé pour son entrée imposante et les bois qui l\'environnent. Le palais impérial est entouré de vastes jardins publics. Les nombreux musées de la ville proposent des expositions allant de l\'art classique, au musée national de Tokyo, à un théâtre kabuki reconstruit, au musée d\'Edo-Tokyo.'),
    (4, 'São Paulo', '1020', 'São Paulo, centre financier dynamique du Brésil, compte parmi les villes les plus populeuses du monde, et possède de nombreuses institutions culturelles et une riche tradition architecturale. Ses bâtiments emblématiques vont de sa cathédrale néogothique au gratte-ciel Prédio Martinelli de 1929, en passant par l\'Edifício Copan de l\'architecte moderniste Oscar Niemeyer. Le Pátio do Colégio de style colonial marque l\'endroit où les prêtres jésuites ont fondé la ville en 1554.'),
    (5, 'Jakarta', '760', 'Jakarta, capitale de l\'Indonésie, se trouve sur la côte nord-ouest de l\'île de Java. Un mélange historique de cultures - javanaise, malaise, chinoise, arabe, indienne et européenne - a influencé son architecture, sa langue et sa cuisine. La vieille ville, Kota Tua, abrite des édifices coloniaux néerlandais, Glodok et le vieux port de Sunda Kelapa, où sont amarrées les goélettes traditionnelles en bois.'),
    (6, 'Port-au-Prince', '950', 'Port-au-Prince est la capitale et la ville la plus peuplée d\'Haïti. La population de la ville était estimée à 987 311 habitants en 2015, la zone métropolitaine étant estimée à 2 618 894 habitants.'),
    (7, 'Berlin', '160', 'La capitale allemande, Berlin, est née au XIIIe siècle. Le Mémorial de l’Holocauste et les pans restants du mur de Berlin, sur lesquels des graffitis ont été peints, témoignent de son passé tumultueux. Divisé en deux pendant la guerre froide, le pays a adopté la porte de Brandebourg du XVIIIe siècle comme symbole de sa réunification. La ville est aussi réputée pour sa scène artistique et ses monuments modernes, comme la Philharmonie de Berlin, un bâtiment doré construit en 1963 dont le toit présente une forme géométrique particulière. '),
    (8, 'Séoul', '790', 'Capitale de la Corée du Sud, Séoul est une immense métropole où les gratte-ciel modernes, les métros high-tech et la culture pop côtoient les temples bouddhistes, les palais et les marchés de rue. Les attractions notables incluent le futuriste Dongdaemun Design Plaza, un palais des congrès aux formes courbes avec un parc sur son toit, le palais de Gyeongbokgung, qui disposait autrefois de plus de 7 000 pièces, et le temple Jogyesa, où se trouvent des pins et des acacias plusieurs fois centenaires.'),
    (9, 'Shanghai', '810', 'Sur la côte centrale de la Chine, Shanghai est la plus grande ville du pays, mais aussi un centre financier international. En son cœur se trouve le Bund, une célèbre promenade au bord de l\'eau où s\'alignent des immeubles de l\'époque coloniale. En face du fleuve Huangpu se dresse le district futuriste de Pudong, avec notamment la tour Shanghai, de 632 m de haut, et la tour de télévision Perle de l\'Orient, qui se caractérise par ses sphères roses. Le vaste jardin Yuyuan inclut des pavillons traditionnels, des tours et des bassins.'),
    (10, 'New York', '1210', 'New York est une ville composée de 5 arrondissements à l\'embouchure du fleuve Hudson et de l\'océan Atlantique. En son centre se trouve Manhattan, un arrondissement densément peuplé faisant partie des principaux centres commerciaux, financiers et culturels du monde. Ses sites incontournables comprennent des gratte-ciel comme l\'Empire State Building et l\'immense Central Park. Le théâtre de Broadway est situé sur Times Square.'),
    (11, 'México', '1090', 'Situé entre les États-Unis et l\'Amérique centrale, Mexico est la capital du Mexique, réputé pour ses plages du Pacifique et du golfe du Mexique, ainsi que pour ses paysages variés - entre montagnes, déserts et jungles. Il est pourvu de ruines anciennes comme Teotihuacan et la cité maya de Chichén Itzá ainsi que de villes datant de l\'époque coloniale espagnole. Sa capitale Mexico propose un retour à la modernité avec ses boutiques haut de gamme, ses musées renommés et ses restaurants gastronomiques.'),
    (12, 'Moscou', '440', 'Moscou, sur la Moskva à l\'ouest de la Russie, est la capitale cosmopolite du pays. Son centre historique est le Kremlin, résidence du Président et complexe abritant les trésors des tsars dans l\'Armurerie. À l\'extérieur, la place Rouge est le centre symbolique de la Russie. Le mausolée de Lénine, le Musée historique d\'État et sa vaste collection, et la cathédrale Saint-Basile-le-Bienheureux, célèbre pour ses clochers à bulbe colorés, y sont implantés.'),
    (13, 'Los Angeles', '1450', 'os Angeles, ville tentaculaire en Californie du Sud, est le cœur de l\'industrie cinématographique et télévisuelle. Non loin du panneau Hollywood, les studios Paramount Pictures, Universal et Warner Brothers proposent des visites pour découvrir leurs installations. Sur Hollywood Boulevard, devant le Grauman\'s Chinese Theatre, les plus grandes stars du cinéma ont laissé leurs empreintes de pieds et de mains dans le ciment. Le Walk of Fame honore des milliers de célébrités et les vendeurs proposent des plans répertoriant les maisons des stars.'),
    (14, 'Dacca', '480', 'Dacca est la capitale du Bangladesh, en Asie du Sud. Située au bord de la Buriganga, cette ville est le centre du gouvernement, du commerce et de la culture du pays. La vieille ville, édifiée au XVIIe siècle, était la capitale moghole du Bengale, et de nombreux palais et mosquées de cette époque subsistent. Le complexe du Parlement du Bangladesh, conçu par l\'architecte américain Louis Khan, est un exemple typique de l\'expansion rapide que connaît cette immense métropole moderne.'),
    (15, 'Buenos Aires', '260', 'Buenos Aires est la plus grande métropole cosmopolite de l\'Argentine. La Plaza de Mayo, située au centre de la ville, est bordée de bâtiments majestueux du XIXe siècle dont la Casa Rosada, l\'emblématique palais présidentiel avec son balcon-galerie. Parmi les autres monuments importants figurent le Teatro Colón, une grande salle d\'opéra d\'environ 2 500 places inaugurée en 1908, et le MALBA, musée d\'art latino-américain.'),
    (16, 'Istanbul', '350', 'Istanbul est une grande ville turque à cheval entre l\'Europe et l\'Asie, séparée par le détroit du Bosphore. Sa vieille ville reflète les influences culturelles des nombreux empires qui ont régné sur Istanbul. Dans le quartier de Sultanahmet, l\'hippodrome en plein air datant de l\'époque romaine a accueilli des courses de chars pendant plusieurs siècles, et des obélisques égyptiens sont toujours debout. L\'emblématique musée byzantin Sainte-Sophie comprend un imposant dôme du VIe siècle et des mosaïques chrétiennes rares.'),
    (17, 'Rio de Janeiro', '1350', 'Rio de Janeiro est une grande ville du Brésil située en bord de mer. Elle est connue pour ses plages de Copacabana et d\'Ipanema, sa statue du Christ Rédempteur de 38 mètres de haut située au sommet du Corcovado et le Pain de Sucre, pic de granite dont le sommet est accessible via 2 téléphériques. La ville est également connue pour ses favelas tentaculaires (bidonvilles). Le célèbre carnaval de Rio, avec défilés de chars, costumes flamboyants et danseurs de samba, est considéré comme l\'un des plus grands au monde.'),
    (18, 'Lima', '820', 'Lima, la capitale du Pérou, se trouve sur la côte pacifique aride du pays. Malgré son centre colonial préservé, il s\'agit d\'une métropole bouillonnante comptant parmi les plus grandes d\'Amérique du Sud. Elle abrite le musée Larco et sa collection d\'art précolombien, ainsi que le Musée de la nation, qui retrace l\'histoire des anciennes civilisations du Pérou. La place d\'Armes et la cathédrale du XVIe siècle sont au cœur du vieux Lima.'),
    (19, 'Milan', '180', 'Milan, métropole de la région de la Lombardie, au nord de l\'Italie, est une capitale mondiale de la mode et du design. Ce centre financier, siège de la Bourse d\'Italie, est également connu pour ses boutiques et ses restaurants haut de gamme. La cathédrale gothique du Dôme de Milan et le couvent Santa Maria delle Grazie, qui abrite la fresque de Léonard de Vinci, La Cène, témoignent de plusieurs siècles d\'art et de culture.'),
    (20, 'Chicago', '1350', 'Sur le lac Michigan, dans l\'État de l\'Illinois, Chicago fait partie des plus grandes villes des États-Unis. Célèbre pour son architecture audacieuse, sa ligne d\'horizon est ponctuée de gratte-ciel comme l\'emblématique John Hancock Center, la Willis Tower et la néogothique Tribune Tower. La ville est également réputée pour ses musées, notamment l\'institut d\'Art de Chicago et ses remarquables œuvres impressionnistes et post-impressionnistes.');


INSERT INTO transports (transport_id, transport_name, transport_prix) VALUES 
    (1, 'Avion', '100'),
    (2, 'Train', '50'),
    (3, 'Bateau', '80'),
    (4, 'Bus', '30');

INSERT INTO logements (logement_id, logement_name, logement_prix) VALUES 
    (1, 'Hôtel', '100'),
    (2, 'Appartement', '50'),
    (3, 'Gîtes', '60'),
    (4, 'Chambres d\'hôtes', '55'),
    (5, 'Camping', '46'),
    (6, 'Auberges', '59'),
    (7, 'Co-living', '25'),
    (8, 'Échange de maisons', '40');