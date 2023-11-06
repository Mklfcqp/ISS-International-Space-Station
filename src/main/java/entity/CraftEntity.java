package entity;

public class CraftEntity {










    @OneToOne
    @JoinColumn(name="craft_id")
    CraftEntity craft;
}
