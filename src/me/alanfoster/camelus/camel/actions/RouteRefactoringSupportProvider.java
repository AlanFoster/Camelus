package me.alanfoster.camelus.camel.actions;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.RefactoringActionHandler;
import org.jetbrains.annotations.NotNull;

/**
 * Provides refactoring support for the highlighted section of a camel route and extracts it into a
 * new camel route.
 *
 * @see ExtractRouteTemplateActionHandler
 */
public class RouteRefactoringSupportProvider extends RefactoringSupportProvider {

    @Override
    public boolean isAvailable(@NotNull PsiElement context) {
      //  PsiFile containingFile = context.getContainingFile();
      //  return containingFile != null && BlueprintManager.getInstance().isCamelRoute(containingFile);
        return super.isAvailable(context);
    }

    @Override
    public RefactoringActionHandler getExtractMethodHandler() {
        return new ExtractRouteTemplateActionHandler();
    }
}
