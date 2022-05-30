package models;

public class OrderData {
    private int id;
    private int userId;
    private UserData user;
    private int exhibitionId;
    private ExhibitionData exhibition;
    private String exhibitionName;
    private int quantity;
    private int sumForExhibition;
    private int sum;

    public int getSumForExhibition() {
        return sumForExhibition;
    }

    public void setSumForExhibition(int sumForExhibition) {
        this.sumForExhibition = sumForExhibition;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    public ExhibitionData getExhibition() {
        return exhibition;
    }

    public void setExhibition(ExhibitionData exhibition) {
        this.exhibition = exhibition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "id=" + id +
                ", userId=" + userId +
                ", user=" + user +
                ", exhibitionId=" + exhibitionId +
                ", exhibition=" + exhibition +
                ", exhibitionName='" + exhibitionName + '\'' +
                ", quantity=" + quantity +
                ", sumForExhibition=" + sumForExhibition +
                ", sum=" + sum +
                '}';
    }
}
