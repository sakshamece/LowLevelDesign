class User {
    private int userId;
    private String name;
    private String details;

    public void renewMembership()
    {
    }

    public User(int id, String details, String name)
    {
        this.userId = id;
        this.details = details;
        this.name = name;
    }

    public int getId()
    {
        return userId;
    }

    public void setId(int id)
    {
        userId = id;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
