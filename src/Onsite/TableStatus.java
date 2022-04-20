/**
 * Group 19
 * Aurélien Giuglaris Michael & Antoine Dorard
 * i6279204 & i6269522
 */
package Onsite;

public enum TableStatus {
    PENDING, // Clients still need to order
    HAS_EATEN, // Clients will pay
    COMPLETED, // Clients have paid, ready for table reset
}
