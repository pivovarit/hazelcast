package com.hazelcast.internal.env;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ElementAdapter implements Element {
    private final ConfigNode configNode;

    ElementAdapter(ConfigNode yamlNode) {
        this.configNode = yamlNode;
    }

    @Override
    public String getNodeName() {
        return configNode.getName();
    }

    @Override
    public String getNodeValue() throws DOMException {
        return configNode.getValue();
    }

    @Override
    public short getNodeType() {
        return Node.ELEMENT_NODE;
    }

    @Override
    public Node getParentNode() {
        return new ElementAdapter(configNode.getParent());
    }

    @Override
    public NodeList getChildNodes() {
        return new NodeList() {
            private final List<Node> children = configNode.getChildren().values().stream()
              .map(ElementAdapter::new)
              .collect(Collectors.toList());

            @Override
            public Node item(int index) {
                return children.get(index);
            }

            @Override
            public int getLength() {
                return children.size();
            }
        };
    }

    @Override
    public void setNodeValue(String nodeValue) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node getFirstChild() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node getLastChild() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node getPreviousSibling() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node getNextSibling() {
        throw new UnsupportedOperationException();
    }

    @Override
    public NamedNodeMap getAttributes() {
        return new NamedNodeMap() {

            @Override
            public Node getNamedItem(String name) {
                return new ElementAdapter(configNode.getChildren().get(name));
            }

            @Override
            public Node setNamedItem(Node arg) throws DOMException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Node removeNamedItem(String name) throws DOMException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Node item(int index) {
                return new ElementAdapter(new ArrayList<>(configNode.getChildren().values()).get(index));
            }

            @Override
            public int getLength() {
                return configNode.getChildren().size();
            }

            @Override
            public Node getNamedItemNS(String namespaceURI, String localName) throws DOMException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Node setNamedItemNS(Node arg) throws DOMException {
                throw new UnsupportedOperationException();
            }

            @Override
            public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Document getOwnerDocument() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node removeChild(Node oldChild) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Node appendChild(Node newChild) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasChildNodes() {
        return !configNode.getChildren().isEmpty();
    }

    @Override
    public Node cloneNode(boolean deep) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void normalize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSupported(String feature, String version) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getNamespaceURI() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPrefix() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPrefix(String prefix) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getLocalName() {
        return getNodeName();
    }

    @Override
    public boolean hasAttributes() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getBaseURI() {
        throw new UnsupportedOperationException();
    }

    @Override
    public short compareDocumentPosition(Node other) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getTextContent() throws DOMException {
        return getNodeValue();
    }

    @Override
    public void setTextContent(String textContent) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSameNode(Node other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String lookupPrefix(String namespaceURI) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDefaultNamespace(String namespaceURI) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String lookupNamespaceURI(String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEqualNode(Node arg) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getFeature(String feature, String version) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object setUserData(String key, Object data, UserDataHandler handler) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getUserData(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getTagName() {
        return getNodeName();
    }

    @Override
    public String getAttribute(String name) {
        return "";
    }

    @Override
    public void setAttribute(String name, String value) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAttribute(String name) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Attr getAttributeNode(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Attr setAttributeNode(Attr newAttr) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public NodeList getElementsByTagName(String name) {
        return new SingletonNodeList(getAttributes().getNamedItem(name));
    }

    @Override
    public String getAttributeNS(String namespaceURI, String localName) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Attr getAttributeNodeNS(String namespaceURI, String localName) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasAttribute(String name) {
        return getAttributes().getNamedItem(name) != null;
    }

    @Override
    public boolean hasAttributeNS(String namespaceURI, String localName) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIdAttribute(String name, boolean isId) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException {
        throw new UnsupportedOperationException();
    }

    static class SingletonNodeList implements NodeList {

        private final Node element;

        public SingletonNodeList(Node element) {
            this.element = element;
        }

        @Override
        public Node item(int index) {
            if (index != 0) {
                return null;
            }
            return element;
        }

        @Override
        public int getLength() {
            return 0;
        }
    }
}
