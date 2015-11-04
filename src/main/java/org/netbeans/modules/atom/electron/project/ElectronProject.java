package org.netbeans.modules.atom.electron.project;

import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author spindizzy
 */
class ElectronProject implements Project {

    private final FileObject projectDir;
    private final ProjectState projectState;
    private Lookup lookup;

    ElectronProject(FileObject projectDir, ProjectState state) {
        this.projectDir = projectDir;
        this.projectState = state;
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    @Override
    public Lookup getLookup() {
        //TODO register customizer for project properties here
        if (lookup == null) {
            lookup = Lookups.fixed(new Object[]{
                this,
                new ElectronProjectInformation(this),
                new ElectronProjectLogicalView(this),
                new ElectronProjectDeleteOperation(this),
                new ElectronActionsProvider(this)
            });
        }
        return lookup;
    }

    ProjectState getProjectState() {
        return projectState;
    }
}
