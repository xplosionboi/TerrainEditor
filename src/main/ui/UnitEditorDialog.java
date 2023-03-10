package ui;

import model.BattleClass;
import model.Faction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class representing a dialog to edit units
 */
public class UnitEditorDialog extends JDialog implements ActionListener {

    public static final String CANCEL = "Cancel";
    public static final String OK = "OK";
    Faction faction;
    BattleClass battleClass;

    JComboBox<Faction> factionBox;
    JComboBox<BattleClass> battleClassBox;

    private boolean cancelled = true;

    /**
     * Constructs a UnitEditor dialog with the given faction and battle class initially selected
     *
     * @param owner owner of the dialog
     * @param faction initial Faction selection
     * @param battleClass initial BattleClass selection
     */
    public UnitEditorDialog(JFrame owner, Faction faction, BattleClass battleClass) {
        super(owner, "Unit Editor");
        setModal(true);
        initializeComponents(faction, battleClass);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 0, 13));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    /**
     * Returns whether the dialog was cancelled by the user
     *
     * @return if the dialog was cancelled
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Initializes all components of the dialog
     *
     * @param faction the initial Faction selection
     * @param battleClass the initial BattleClass selection
     */
    private void initializeComponents(Faction faction, BattleClass battleClass) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel dropdownPanel = new JPanel(new GridLayout(2, 2));
        factionBox = new JComboBox<>(Faction.values());
        battleClassBox = new JComboBox<>(BattleClass.values());
        factionBox.setSelectedItem(faction);
        battleClassBox.setSelectedItem(battleClass);
        JButton cancelButton = new JButton(CANCEL);
        cancelButton.setActionCommand(CANCEL);
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);
        JButton okButton = new JButton(OK);
        okButton.setActionCommand(OK);
        okButton.addActionListener(this);
        buttonPanel.add(okButton);
        dropdownPanel.add(new JLabel("Select Faction:"));
        dropdownPanel.add(factionBox);
        dropdownPanel.add(new JLabel("Select Class:"));
        dropdownPanel.add(battleClassBox);
        add(dropdownPanel);
        add(buttonPanel);
    }

    /**
     * Returns the selected Faction
     *
     * @return the selected Faction
     */
    public Faction getFaction() {
        return faction;
    }

    /**
     * Returns the selected BattleClass
     *
     * @return the selected BattleClass
     */
    public BattleClass getBattleClass() {
        return battleClass;
    }

    /**
     * Handles user input when closing the dialog
     * Code modeled after http://www2.hawaii.edu/~takebaya/ics111/jdialog/jdialog.html
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(OK)) {
            faction = (Faction) factionBox.getSelectedItem();
            battleClass = (BattleClass) battleClassBox.getSelectedItem();
            cancelled = false;
        }
        dispose();
    }

}
