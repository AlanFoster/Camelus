package me.alanfoster.intellij.blueprint.language.contributors;

import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import me.alanfoster.intellij.blueprint.language.psi.InjectionPropertyDefinition;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 */
public class InjectionPsiReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
        ElementPattern pattern = PlatformPatterns.psiElement(InjectionPropertyDefinition.class);

        registrar.registerReferenceProvider(pattern, new PsiReferenceProvider() {
            @NotNull
            @Override
            public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
              // Method never triggered
             return PsiReference.EMPTY_ARRAY;
            }
        });
    }


    // return new PsiReference[] { new InjectionPsiReference(null) };
    /*
       ElementPattern pattern =
          xmlAttributeValue()
                .withLocalName("value")
                .withParent(
                        xmlAttribute()
                                .withLocalName("value")
                                .withParent(
                                        xmlTag()
                                                .withLocalName(StandardPatterns.string().oneOf("property", "argument"))
                                                .withParent(
                                                        xmlTag()
                                                                .withLocalName("bean")
                                                )
                                )
                );*/
}
