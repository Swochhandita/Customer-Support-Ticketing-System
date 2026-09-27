function TicketList(){
    const tickets= [
        {
            id: 101,
            title: "Login issue",
            status: "OPEN"
        },
        {
            id: 102,
            title: "Payment issue",
            status: "RESOLVED"
        },
        {
            id: 103,
            title: "Password issue",
            status: "OPEN"
        }
    ];

    return(
        <div>
            <h1>Ticket List</h1>
            {tickets.map(ticket=>(
                <div key={ticket.id}>
                    <h3>Ticket #{ticket.id}</h3>
                    <h3>{ticket.title}</h3>
                    <h3>Status: {ticket.status}</h3>
                </div>
            ))}
        </div>
    );
}
export default TicketList;