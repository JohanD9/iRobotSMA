/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import interfaces.IControl;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Johan
 */
public class XmlToPersist {
    Grille grille;
    public IControl control;

    public XmlToPersist(Grille grille, IControl ctrl) {
        this.grille = grille;
        this.control = ctrl;
    }
    
    public void saveGrilleInXmlFile() {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MainFraime.class.getName()).log(Level.SEVERE, null, ex);
        }

        // root elements
        org.w3c.dom.Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("grille");
        doc.appendChild(rootElement);

        for (int i = 0; i < grille.nbLigne; i++) {
            for (int j = 0; j < grille.nbColonne; j++) {
                Case c = grille.getCasePanelTable()[i][j];
                
                if (c.listComposants.size() > 0) {
                    Element eCase = doc.createElement("case");
                    Attr ordonne = doc.createAttribute("ordonne");
                    ordonne.setValue(String.valueOf(c.ordonneeCase));
                    eCase.setAttributeNode(ordonne);
                    Attr abscisse = doc.createAttribute("abscisse");
                    abscisse.setValue(String.valueOf(c.abscisseCase));
                    eCase.setAttributeNode(abscisse);
                    

                    for (Composant comp : c.listComposants) {
                        Element eComp = doc.createElement("composant");

                        Element eId = doc.createElement("id");
                        eId.appendChild(doc.createTextNode(String.valueOf(comp.id)));
                        eComp.appendChild(eId);

                        Element eType = doc.createElement("type");
                        eType.appendChild(doc.createTextNode(comp.type.toString()));
                        eComp.appendChild(eType);

                        Element eCouleur = doc.createElement("couleur");
                        eCouleur.appendChild(doc.createTextNode(comp.couleur.toString()));
                        eComp.appendChild(eCouleur);

                        eCase.appendChild(eComp);

                    }
                    rootElement.appendChild(eCase);
                }
            }
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(MainFraime.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JFileChooser chooser = new JFileChooser();
        File file = null;
        chooser.setCurrentDirectory(new File("/home/me/Documents"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                file = chooser.getSelectedFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        if (file != null) {
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            try {
                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(source, result);
            } catch (TransformerException ex) {
                Logger.getLogger(MainFraime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void loadGrilleFromXml() throws ParserConfigurationException, SAXException, java.io.IOException {
        File file = null;
        JFileChooser loadFile= new JFileChooser();
        loadFile.setApproveButtonText("Select File");
        loadFile.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter f1 = new FileNameExtensionFilter("xml files (*.xml)", "xml");
        loadFile.setFileFilter(f1);
        
        int retrival = loadFile.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            file = loadFile.getSelectedFile();
            
            if (file != null) {
                
                grille.clearGrille();
                // Parsage du fichier xml
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("case");
 
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    int abscisse = Integer.valueOf(nList.item(temp).getAttributes().getNamedItem("abscisse").getNodeValue());
                    int ordonne = Integer.valueOf(nList.item(temp).getAttributes().getNamedItem("ordonne").getNodeValue());
                    
                    NodeList compList = nList.item(temp).getChildNodes();
                    for (int tempComp = 0; tempComp < compList.getLength(); tempComp++) {
                        if (compList.item(tempComp).getNodeType() == Node.ELEMENT_NODE) {
                            Element eComp = (Element) compList.item(tempComp);
                            String id = eComp.getElementsByTagName("id").item(0).getTextContent();
                            String type = eComp.getElementsByTagName("type").item(0).getTextContent();
                            String couleur = eComp.getElementsByTagName("couleur").item(0).getTextContent();
                            
                            int idReturn = control.charger(new Position(abscisse, ordonne), getType(type), getCouleur(couleur));
                            grille.addComposant(grille.getCasePanelTable()[abscisse][ordonne], idReturn, getType(type), getCouleur(couleur));
                            grille.validate();
                        }
                    }
                }
            }
        }
    }
    
    private Type getType(String type) {
        if (type.equals("ROBOT")) {
            return Type.ROBOT;
        } else if (type.equals("NID")) {
            return Type.NID;
        } else {
            return Type.BOITE;
        }
    }
    
    private Couleur getCouleur(String couleur) {
        if (couleur.equals("RED")) {
            return Couleur.RED;
        } else if (couleur.equals("GREEN")) {
            return Couleur.GREEN;
        } else {
            return Couleur.BLUE;
        }
    }
}
