//package examples.party;
// Imports
///////////////

import jade.core.Agent;
import jade.core.AID;

import jade.domain.FIPAException;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import jade.core.behaviours.CyclicBehaviour;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.DFService;


	public class DHCPClient extends Agent {
	    // Constants
	    //////////////////////////////////


	    // Static variables
	    //////////////////////////////////


	    // Instance variables
	    //////////////////////////////////

	    protected boolean m_knowRumour = false;


	    // Constructors
	    //////////////////////////////////


	    // External signature methods
	    //////////////////////////////////

	    /**
	     * Set up the agent. Register with the DF, and add a behaviour to process
	     * incoming messages.  Also sends a message to the Server to say that this
	     * guest has arrived.
	     */
	    protected void setup() {
		try {
		    // create the agent description of itself
		    ServiceDescription sd = new ServiceDescription();
		    sd.setType( "SimulatedHost" );
		    sd.setName( "HostServiceDescription" );
		    DFAgentDescription dfd = new DFAgentDescription();
		    dfd.setName( getAID() );
		    dfd.addServices( sd );

		    // register the description with the DF
		    DFService.register( this, dfd );

		    // Client broadcasts to locate available servers
		    ACLMessage discover = new ACLMessage( ACLMessage.CFP );
		    discover.setOntology("DHCP-ontology");
		    discover.setContent( DHCPServer.DHCPDISCOVER );
		    discover.addReceiver(new AID( "Luis", AID.ISLOCALNAME ) );
		    send( discover );
		    System.out.println("El " + getLocalName()+" Ha enviado un DHCPDiscover");

		    // add a Behaviour to process incoming messages
		    addBehaviour( new CyclicBehaviour( this ) {
		                    public void action() {
		                        // listen if a greetings message arrives
		                        ACLMessage msg = receive();

		                        if (msg != null) {
		                            if (DHCPServer.DHCPOFFER.equals( msg.getContent() )) {
		                                // Reads then sends request
		                                selectoffer(msg.getSender());
		                            }
		                            else if (DHCPServer.DHCPACK.equals( msg.getContent() )){
		                            	//reads and configures itself
		                            	setConf();
		                            }
		                            else if (DHCPServer.DHCPNAK.equals( msg.getContent() )){
		                            	//resetConv();
		                           
		                            }
		                            else if (DHCPServer.GOODBYE.equals( msg.getContent() )){
		                        		myWatchIsEnded();
				                           
		                            }
		                            else {
		                                System.out.println( "Guest received unexpected message: " + msg );
		                            }
		                        }
		                        else {
		                            // if no message is arrived, block the behaviour
		                            block();
		                        }
		                    }
		                } );
		}
			catch (Exception e) {
		    System.out.println( "Saw exception in GuestAgent: " + e );
		    e.printStackTrace();
			}

	    }
	    
	    //kills the agents
	    private void myWatchIsEnded(){		                            	
		    try {
	    		DFService.deregister( this );
	    		doDelete();
			}
			catch (Exception e) {
	    		System.err.println( "Saw FIPAException while terminating: " + e );
	    		e.printStackTrace();
			}
	    }


	    protected void selectoffer( AID server0 ) {
		    ACLMessage request = new ACLMessage( ACLMessage.INFORM );
		    request.setOntology("DHCP-ontology");
		    request.setContent( DHCPServer.DHCPREQUEST );
		    request.addReceiver( server0 );
		    send( request );
		    System.out.println("El " + getLocalName()+" Ha enviado un DHCPRequest");
		    
	    }

	    protected void setConf(){
	    	
	    }
	    
	    protected void releaseAdd( AID server0 ) {
		    ACLMessage release = new ACLMessage( ACLMessage.INFORM );
		    release.setOntology("DHCP-ontology");
		    release.setContent( DHCPServer.DHCPRELEASE );
		    release.addReceiver( server0 );
		    send( release );
		    System.out.println("El " + getLocalName()+" Ha enviado un DHCPRelease");
		    
	    }
	    


	}
