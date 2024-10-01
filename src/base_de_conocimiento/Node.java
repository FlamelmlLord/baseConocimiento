/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base_de_conocimiento;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String attribute;
    private String value;
    private List<Object> conditions;
    private List<Node> edges;

    public Node(String attribute, String value, List<Object> conditions) {
        this.attribute = attribute;
        this.value = value;
        this.conditions = conditions != null ? conditions : new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public String getAttribute() {
        return attribute;
    }

    public String getValue() {
        return value;
    }

    public List<Object> getConditions() {
        return conditions;
    }

    public void addEdge(Node edge) {
        edges.add(edge);
    }

    public void addCondition(Object condition) {
        conditions.add(condition);
    }
}
