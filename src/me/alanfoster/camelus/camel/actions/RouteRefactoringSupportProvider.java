package me.alanfoster.camelus.camel.actions;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.xml.XmlFile;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.util.xml.DomManager;
import me.alanfoster.camelus.blueprint.dom.model.BlueprintFileDescription;
import me.alanfoster.camelus.blueprint.model.BlueprintManager;
import org.jetbrains.annotations.NotNull;

/**
 * Provides refactoring support for the highlighted section of a camel route and extracts it into a
 * new camel route.
 *
 * @see ExtractRouteTemplateActionHandler
 */
public class RouteRefactoringSupportProvider extends RefactoringSupportProvider {

    /**
     * Route Refactoring support should only be available when calling refactoring
     * under the context of a blueprint file.
     * Specific user error reporting will occur within the handler, if required.
     *
     * @param context refactoring context
     * @return True if the psiElement is within a Blueprint PsiFile
     *         Otherwise false.
     */
    @Override
    public boolean isAvailable(@NotNull PsiElement context) {
        return BlueprintManager.getInstance().isBlueprintFile(context.getContainingFile())
                && super.isAvailable(context);
    }

    @Override
    public RefactoringActionHandler getExtractMethodHandler() {
        return new ExtractRouteTemplateActionHandler();
    }
}
