interface Subject {
    void addObserver(NotificationSender observer);
    void removeObserver(NotificationSender observer);
    void notifyObservers();
}