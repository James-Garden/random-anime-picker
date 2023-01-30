<#--noinspection InjectedReferences-->
<#include 'macros/_staticUrl.ftl'>

<#import 'macros/_defaultHeader.ftl' as defaultHeader>
<#import '/spring.ftl' as spring>

<#macro baseTemplate
  pageTitle=""
  useHeader=true
  headerContent=""
  errorList={}
>
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="${staticUrl("style.css")}">
    <link rel="icon" type="image/x-icon" href="${staticUrl("favicon.ico")}">
  </head>
    <body>
      <#if useHeader>
        <header>
          <#if headerContent?has_content>
            ${headerContent}
          <#else>
            <@defaultHeader.defaultHeader/>
          </#if>
        </header>
      </#if>
      <main>
        <#nested>
      </main>
    </body>
  </html>
</#macro>
