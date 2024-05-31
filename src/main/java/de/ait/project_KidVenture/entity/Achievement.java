package de.ait.project_KidVenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "achievement")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "achievement_description")
    private String achievementDescription;

    @Column(name = "criteria")
    private String criteria;

    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward reward;
}
/*Title (Nosaukums): Tas ir sasnieguma nosaukums vai virsraksts. Šis nosaukums identificē sasniegumu
 un var norādīt uz tā satura būtību vai mērķi. Piemēram, "Math Expert" norāda, ka sasniegums ir saistīts ar matemātikas jomu.
Achievement Description (Sasnieguma apraksts): Tas ir apraksts, kas sniedz detalizētu informāciju par sasniegumu.
Tas var ietvert informāciju par to, kā sasniegums tiek iegūts vai kāds ir tā nozīmīgums. Piemēram, "Šis sasniegums
 ir pats uzcitītāko uzdevumu pildīšana un tā tālāk".
Criteria (Kritēriji): Tas ir noteikumu komplekts vai nosacījumi, kas jāizpilda, lai iegūtu sasniegumu. Šie kritēriji
 var būt saistīti ar veiktajiem uzdevumiem, laika ierobežojumiem vai citiem nosacījumiem. Tavā piemērā kritēriji ir
 jāatrisina vismaz 89% uzdevumu un jāieklaujas laikā 2 minūtes.
Reward (Balva): Tas ir objekts vai saite uz balvu, kas piešķirta par šo sasniegumu. Balva var būt konkrēta objekta,
statusa vai citu privilēģiju piešķiršana. Tavā piemērā balva ir "Golden Cube", kas varētu būt virtuāla apbalvojuma vai speciāla atzinība par sasniegumu. */