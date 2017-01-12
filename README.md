# epic-backend
API Explanation
    
    {URL (HTTP method | input)}
    
###Authentication

    /login (POST | username, password)
    /logout (POST | token)

###Sequence

    /sequence (GET | token)
    /sequence/request (GET | token)
    /sequence/new (POST | token, sequenceNumber, by_user, purpose, date )
    /sequence/delete (DELETE | token, sequence_number)
    
####Sequence List

    /sequences (GET | token)
    /sequences/page (GET | token, page, itemsPerPage, filter )

