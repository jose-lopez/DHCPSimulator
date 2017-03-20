//package examples.party;
// Imports
///////////////

import jade.core.AID;
import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Profile;

import jade.wrapper.PlatformController;
import jade.wrapper.AgentController;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.DFService;
import jade.domain.FIPAException;

import javax.swing.*;
import java.util.*;
import java.text.NumberFormat;

import jpl.*;
import jpl.Integer;


	public class DHCPServer extends Agent {
    


    		public final static String DHCPDISCOVER = "DHCPDISCOVER";
    		public final static String DHCPREQUEST = "DHCPREQUEST";
    		public final static String DHCPRELEASE = "DHCPRELEASE";
    		public final static String DHCPOFFER = "DHCPOFFER";
    		public final static String DHCPACK = "DHCPACK";
    		public final static String DHCPNAK = "DHCPNAK";
    		public final static String DHCPDECLINE = "DHCPDECLINE";
    		public final static String DHCPINFORM = "DHCPINFORM";
    		public final static String GOODBYE = "GOODBYE";

    	    protected Hashtable<Integer,String> poolCatalogue; 

    		protected JFrame m_frame = null;
    		protected JFrame m_frame_1 = null;
    		protected Vector m_hostList = new Vector();    // numero de PC's
    		protected int m_hostCount = 0;                 // arrivals
    		protected int m_rumourCount = 0;
    		protected int m_introductionCount = 0;
    		protected boolean m_simulationOver = false;
    		protected NumberFormat m_avgFormat = NumberFormat.getInstance();
    		protected long m_startTime = 0L;


    		public DHCPServer() {

        	m_avgFormat.setMaximumFractionDigits( 2 );
        	m_avgFormat.setMinimumFractionDigits( 2 );

    		}



    		// External signature methods
   		 //////////////////////////////////

    		/**
     		* Setup the agent.  Registers with the DF, and adds a behavior to
     		* process incoming messages.
     		*/
    		protected void setup() {
    			
    			poolCatalogue= new Hashtable(256);
    			// add the GUI
        		setupUI();
    			
    			System.out.println( getLocalName() + " setting up");
        		// create the agent description of itself
        		DFAgentDescription dfd = new DFAgentDescription();
        		dfd.setName( getAID() );
        		ServiceDescription sd = new ServiceDescription();
        		sd.setType("DHCPServer");
        		sd.setName("JADE-DHCPServer");
        		dfd.addServices(sd);
            	
        		try {
            		DFService.register( this, dfd );
            	}
        		catch (Exception e) {
            		System.out.println( "Saw exception in DHCPServer: " + e );
            		e.printStackTrace();
        		}
            		
            		
        		// add a Behaviour to handle messages from hosts
        		addBehaviour( new CyclicBehaviour( this ) {
                        		public void action() {
                            		ACLMessage msg = myAgent.receive();
                            			if (msg != null) {
                            				
                            				if (DHCPDISCOVER.equals( msg.getContent() )) {
                                    		// A host wants to connect
                            				offeraddress(msg.getSender());
                                    	
                            				}
                                		
                            				else if (DHCPREQUEST.equals( msg.getContent() )) {
                                    		// The host ask for offered parameters
                                			sendpack(msg.getSender());
                                    
                            				}

                            				else if (DHCPRELEASE.equals( msg.getContent() )) {
                                				// The host wants to disconnect
                                			//freeaddress();
                                			
                            				}
                            			}
                            			else {
                                		// if no message is arrived, block the behaviour
                                		block();
                            			}
                        		}
        		} );
        		

    		}
    		
    		// Put agent clean-up operations here
    		protected void takeDown() {
    		// Deregister from the yellow pages
    			m_simulationOver = true;
    			try {
    				DFService.deregister(this);
    			}
	    		catch (FIPAException fe) {
	    			fe.printStackTrace();
	    		}
	    		// Close the GUI
	    		m_frame_1.dispose();
	    		// Printout a dismissal message
	    		System.out.println("DHCPServer "+getAID().getLocalName()+" terminating.");
	    		System.out.println( "Simulation run complete. NHosts = " + m_hostCount + ", time taken = " +
                		m_avgFormat.format( ((double) System.currentTimeMillis() - m_startTime) / 1000.0 ) + "s" );
        		// send a message to all host to tell them to leave
        		for (Iterator i = m_hostList.iterator();  i.hasNext();  ) {
            		ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
            		msg.setContent( GOODBYE );

            		msg.addReceiver( (AID) i.next() );

            		send(msg);
        		}

        		m_hostList.clear();
    		}
    		
    		
    		//This is invoked by the GUI when the user adds a new IP address to the pool
    		public void updateCatalogue(final Integer i, final String ipAdd) {
    			addBehaviour(new OneShotBehaviour() {
    				public void action() {
    					poolCatalogue.put(i, ipAdd);
    					System.out.println("Se ha añadido "+ipAdd+" al pool de direcciones, en la posicion = "+i);
    				}
    			} );
    		}


    		/**
     		* Setup the GUI
     		*/
    		private void setupUI() {
        		m_frame_1 = new MainUIFrame( this );  
        		m_frame_1.setLocation( 400, 400 );
        		m_frame_1.setVisible( true );
        		m_frame_1.validate();
    		}
    		
    		//Sends Offer
    		
    		int j = 1;
    		
    		protected void offeraddress( AID host0 ) {
    			
    			   			
    			if (!poolCatalogue.isEmpty()){ 
    				
	    			//String ipAdd = poolCatalogue.get(j);
	    			ACLMessage offer = new ACLMessage(ACLMessage.PROPOSE);
	    			offer.setOntology("DHCP-ontology");
	    			offer.setContent( DHCPOFFER);
	    			offer.addReceiver(host0);
			        send( offer );
			        System.out.println("El servidor " + getLocalName()+" Ha enviado un DHCPOffer al "+host0.getLocalName());
			        //System.out.println(ipAdd);
			        j++;
    			}
    			else{
    				
    				System.out.println("No hay direcciones disponibles en este momento");
    			}
    				
    		}
    		
    		//Sends configuration parameters
    		
    		protected void sendpack( AID host0 ) {
    			ACLMessage pack = new ACLMessage(ACLMessage.INFORM);
    		    pack.setOntology("DHCP-ontology");
    			pack.setContent( DHCPACK );
    			pack.addReceiver(host0);
		        send( pack );
		        System.out.println("El servidor " + getLocalName()+" Ha enviado un DHCPAck al "+host0.getLocalName());
    				
    		}
    		
    		private void freeaddress() {
    			
    			
    			
    		}

    		/**
     		* Invite a number of guests, as determined by the given parameter.  Clears old
     		* state variables, then creates N guest agents.  A list of the agents is maintained,
     		* so that the host can tell them all to leave at the end of the party.
     		*
     		* @param nHosts The number of guest agents to invite.
     		*/
    		protected void inviteHosts( int nHosts) {

        		//System.out.println(t1 + " "+ (q1.hasSolution() ? "succeed" : "failed"));
    			m_hostList.clear();
        		m_hostCount = 0;
        		m_simulationOver = false;
        		// notice the start time
        		m_startTime = System.currentTimeMillis();
        		

			PlatformController container = getContainerController(); // get a container controller for creating new agents
        		// create N guest agents
        		try {
            		for (int i = 0;  i < nHosts;  i++) {
                		// create a new agent
				String localName = "Host_"+i;
				
				//Not all hosts connect at the same time
				
				Thread.sleep((long)(Math.random()*4000));
				
				//
				
				//AgentController host = container.createNewAgent(localName, "examples.party.DHCPClient", null);
				AgentController host = container.createNewAgent(localName, "DHCPClient", null);
				
				host.start();


                		// keep the guest's ID on a local list
                		m_hostList.add( new AID(localName, AID.ISLOCALNAME) );
            		}
        		}
        		catch (Exception e) {
            		System.err.println( "Exception while adding hosts: " + e );
            		e.printStackTrace();
        		}
    		}
 


    		/**
     		* Shut down the host agent, including removing the UI and deregistering
     		* from the DF.
     		*/
    		protected void terminateServer() {
        		try {
            		DFService.deregister( this );
            		m_frame_1.dispose();
            		doDelete();
        		}
        		catch (Exception e) {
            		System.err.println( "Saw FIPAException while terminating: " + e );
            		e.printStackTrace();
        		}
    		}



			public void endSimulation() {
				// TODO Auto-generated method stub
				
			}



			


}

